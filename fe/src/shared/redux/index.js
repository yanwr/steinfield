import { combineReducers } from 'redux';

import userState from '../user/User.reducer';
import homeState from '../../pages/HomePage/HomePage.reducer';

const appReducer = combineReducers({
    userState,
    homeState,
});

const routerReducer = (state, action) => {
    return appReducer(state, action);
}

export default routerReducer;