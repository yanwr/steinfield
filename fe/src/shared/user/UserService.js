import { decode } from 'jsonwebtoken';

export const getDataToke = (token) => {
    const _token = token.replace('Bearer ', '');
    const userDecode = decode( _token, { complete: true });
    const { sub, name, email, admin } = userDecode.payload;
    return { id: sub, name, email, admin, access_token: token };
}

export const setUserInLocalStorage = (user) => {
    localStorage.setItem('userId', user.id);
    localStorage.setItem('userEmail', user.email);
    localStorage.setItem('userName', user.name);
    localStorage.setItem('userIsAdmin', user.admin);
};


export const getUserFromLocalStorage = () => {
    const user = {};
    user.id = localStorage.getItem('userId');
    user.email = localStorage.getItem('userEmail');
    user.name = localStorage.getItem('userName');
    user.admin = localStorage.getItem('userIsAdmin');
    return user;
}

export const clearUserFromLocalStorage = () => {
    localStorage.clear();
}