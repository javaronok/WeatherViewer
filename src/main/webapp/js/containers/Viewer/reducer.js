import createReducer from "../../modules/ReducerUtil";
import * as RequestActions from "../../modules/RequestActions";

const initialState = {
    coords: [],
    city: null,
    data: {}
};
const viewerReducer = createReducer(initialState, {
    [RequestActions.WEATHER_GET_SUCCESS](state, action) {
        console.log("Viewer reducer: received data");
        let {res, coords, city} = action.payload;
        let newState = {...state, coords: coords || [], city, data: res || {}};
        return newState;
    }
});

export default viewerReducer