import createReducer from "./ReducerUtil";
import * as ErrorActions from "./ErrorActions";

const initialState = {
    text: null
};

const errorReducer = createReducer(initialState, {
    [ErrorActions.APPEAR_ERROR](state, action) {
        let error = action.text;
        console.log("Reducer: Appear error" + error);
        return Object.assign({}, state, {text: error});
    },
    [ErrorActions.CLEAR_ERROR](state, action) {
        console.log("Reducer: clear error");
        return Object.assign({}, state, initialState);
    }
});

export default errorReducer