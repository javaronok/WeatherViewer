import createReducer from "./ReducerUtil";
import * as AuthActions from "./AuthActions";

const initialState = {
    isAuth: false,
    userName: null,
    redirectPath: null
};

const authReducer = createReducer(initialState, {
    [AuthActions.LOG_IN](state, action) {
        return Object.assign({}, state, action.data);
    },
    [AuthActions.LOG_OUT](state, action) {
        return Object.assign({}, state, initialState);
    }
});

export default authReducer