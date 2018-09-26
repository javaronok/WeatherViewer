import createReducer from "./ReducerUtil";
import * as RequestActions from "./RequestActions";

const initialState = {
    loading: false
};

const requestReducer = createReducer(initialState, {
    [RequestActions.WEATHER_GET_REQUEST](state, action) {
        console.log("Request reducer: request started ...");
        return Object.assign({}, state, {loading: true});
    },
    [RequestActions.WEATHER_GET_SUCCESS](state, action) {
        console.log("Request reducer: request finished");
        return Object.assign({}, state, initialState);
    },
    [RequestActions.WEATHER_GET_FAILURE](state, action) {
        console.log("Request reducer: request failed");
        return Object.assign({}, state, initialState);
    }
});

export default requestReducer