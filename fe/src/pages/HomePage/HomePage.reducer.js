import HttpRequest from '../../shared/httpRequest/HttpRequest';
import { Logger } from '../../shared/log/Logger';

const Log = new Logger("HomePage.reducer.js");

export const ACTION_TYPES = {
    RESET_STATE: 'home/RESET_STATE',
    LOADING: 'home/LOADING',
    LOAD_PRODUCTS_SUCCESS: 'home/LOAD_PRODUCTS_SUCCESS',
    LOAD_PRODUCTS_FAIL: 'home/LOAD_PRODUCTS_FAIL',
};

const initialState = {
    loading: false,
    products: []
};

export default (state = initialState, action) => {
    switch (action.type) {
        case ACTION_TYPES.RESET_STATE:
            return initialState;
        case ACTION_TYPES.LOADING:
            return {...state, loading: true};
        case ACTION_TYPES.LOAD_PRODUCTS_SUCCESS:
            const products = action.payload;
            return {...state, loading: false, products };
        case ACTION_TYPES.LOAD_PRODUCTS_FAIL:
            if(action.payload && action.payload.hasntData){
                return {...state, loading: false, products: []};
            }
            return {...state, loading: false};
        default:
            return state;
    }
};

export const loadProducts = () => async (dispatch) =>{
    Log.info(`Try to load product list`);
    dispatch({ type: ACTION_TYPES.LOADING });
    try {
        const response = await HttpRequest.get("/products");
        if (response.status === 200) {
            Log.info(`Load products done with success`, response.data);
            dispatch({ 
                type: ACTION_TYPES.LOAD_PRODUCTS_SUCCESS,
                payload: response.data,
            });
        }
    } catch (e) {
        Log.error(`Fail to load products`, e);
        if(e.message.indexOf("Network Error") !== -1 ){
            dispatch({ 
                type: ACTION_TYPES.LOAD_PRODUCTS_FAIL,
                payload: { hasntData: true },
            });
        }
        if(e.response && e.response.status){
            const status = e.response.status;
            if(status === 403 || status === 404 || status === 400){
                alert('Usuário não encontrado ou credencias inválidas, tente novamente !');
            } else if(status === 500) {
                alert('Servidor fora do ar ou erros internos, tente novamente mais tarde !');
            } else {
                alert('Ocorreu um erro, tente novamente !');
            }
        }
        dispatch({ 
            type: ACTION_TYPES.LOAD_PRODUCTS_FAIL,
        });
    }
}

export const handleFilterProduct = (filter) => (dispatch, getState) => {
    const { products } = getState().homeState;
    if(filter === ""){
        loadProducts()(dispatch);
    }
    const productFiltered = products.filter( x => x.name.indexOf(filter) > -1 );
    dispatch({
        type: ACTION_TYPES.LOAD_PRODUCTS_SUCCESS,
        payload: productFiltered
    });
}
