import axios from 'axios';
import HttpRequest from '../httpRequest/HttpRequest';
import  { Logger } from '../log/Logger';
import { 
    getDataToke, 
    clearUserFromLocalStorage,
    getUserFromLocalStorage, 
    setUserInLocalStorage 
} from './UserService';

const Log = new Logger("User.reducer.js");

export const ACTION_TYPES = {
    RESET_STATE: 'session/RESET_STATE',
    LOADING: 'session/LOADING',
    LOGIN_SUCCESS: 'session/LOGIN_SUCCESS',
    LOGIN_FAIL: 'session/LOGIN_FAIL',
    REGISTER_SUCCESS: 'session/REGISTER_SUCCESS',
    REGISTER_FAIL: 'session/REGISTER_FAIL',
    LOGOUT_SUCCESS: 'session/LOGOUT_SUCCESS',
    LOGOUT_FAIL: 'session/LOGOUT_FAIL'
};

const initialState = {
    loading: false,
    user:{}
};

export default (state = initialState, action) => {
    switch (action.type) {
        case ACTION_TYPES.RESET_STATE:
            return initialState;
        case ACTION_TYPES.LOADING:
            return {...state, loading: true};
        case ACTION_TYPES.LOGIN_SUCCESS:
            const user = action.payload;
            return {...state, loading: false, user};
        case ACTION_TYPES.LOGIN_FAIL:
        case ACTION_TYPES.REGISTER_FAIL:
        case ACTION_TYPES.LOGOUT_SUCCESS:
        case ACTION_TYPES.LOGOUT_FAIL:
            return {...state, loading: false};
        default:
            return state;
    }
};

export const doLogin = (email, password, navigation) => async (dispatch) => {
    dispatch({ type: ACTION_TYPES.LOADING });
    try {
        Log.info(`Try to do login with email ${email}.`);
        const body = { 
            email,
            password
         };
        const response = await HttpRequest.post('/login', body);
        if (response.status === 200) {
            const token = response.headers.authorization;
            Log.info(`Access token ${token}`);
            if (token) { 
                const user = getDataToke(token);
                Log.info(`User in session`, user);
                if(user && user.id) {
                    setUserInLocalStorage(user);
                    dispatch({
                        type: ACTION_TYPES.LOGIN_SUCCESS,
                        payload: user
                    });
                    navigation.push('/');
                    Log.info(`Login done with success. userId: ${user.id}`);
                    alert('Login feito com sucesso !');
                }
            } else {
                Log.warn(`Token didn\'t find with email ${email}`);
                axios.defaults.headers.common['Authorization'] = null;
                alert('Token não encontrado, tente logar novamente !');
            }
        } 
    } catch (e) {
        Log.error(`Fail to do login with email ${email}`, e);
        const status = e.response.status;
        if(status === 403 || status === 404 || status === 400){
            alert('Usuário não encontrado ou credencias inválidas, tente novamente !');
        } else if(status === 500) {
            alert('Servidor fora do ar ou erros internos, tente novamente mais tarde !');
        } else {
            alert('Ocorreu um erro, tente novamente !');
        }
        dispatch({ type: ACTION_TYPES.LOGIN_FAIL });
    }
};

export const registerNewUser = (name, email, password, phone, navigation) => async (dispatch) =>{
    Log.info(`Try to do register.`, {name, email, phone });
    dispatch({ type: ACTION_TYPES.LOADING });
    try {
        const body = { 
            name,
            email,
            phone,
            password
         };
        const response = await HttpRequest.post('/users', body);
        if (response.status === 200 || response.status === 201) {
            Log.info(`Register new user with success with email ${email} and will go login`);
            doLogin(email, password, navigation)(dispatch);
        } 
    } catch (e) {
        Log.error(`Fail to register a new user ${{name, email, phone }}`, e);
        const status = e.response.status;
        if(status === 403 || status === 404 || status === 400){
            alert('Credenciais inválidas, tente novamente !');
        } else if(status === 500) {
            alert('Servidor fora do ar ou com erros internos, tente novamente mais tarde !');
        } else {
            alert('Ocorreu um erro, tente novamente !');
        }
        dispatch({ type: ACTION_TYPES.REGISTER_FAIL });
    }
}

const resetState = () => (dispatch) => {
    Log.info(`Try to reset reducer states`);
    dispatch({
        type: ACTION_TYPES.RESET_STATE
    });
    Log.info(`Reducer state reseted done with success`);
}

export const doLogout = (navigation, userId) => (dispatch) => {
    Log.info(`Try to do logout from user ${userId}`);
    dispatch({ type: ACTION_TYPES.LOADING });
    try { 
        clearUserFromLocalStorage();
        dispatch({ type: ACTION_TYPES.LOGOUT_SUCCESS });
        resetState()(dispatch);
        Log.info(`Logout done with success`);
        navigation.push('/');
    } catch(e) {
        Log.error(`Fail to do logout from user ${userId}`, e);
        dispatch({ type: ACTION_TYPES.LOGOUT_FAIL });
    }
}
