import { httpGet } from '../helpers/network'

import * as ErrorActionTypes from './ErrorActions'

export const WEATHER_GET_REQUEST = 'WEATHER_GET_REQUEST';
export const WEATHER_GET_SUCCESS = 'WEATHER_GET_SUCCESS';
export const WEATHER_GET_FAILURE = 'WEATHER_GET_FAILURE';


export function requestWeatherDataByCoords({coords}) {
    return dispatch => {
        dispatch({
            type: WEATHER_GET_REQUEST,
        });

        const url = '/weather/weather_by_coords';
        let params = {lat: coords.latitude, lon: coords.longitude};
        httpGet(url, params)
            .then(res => {
                console.log("Success: " + res);
                dispatch({
                    type: WEATHER_GET_SUCCESS,
                    payload: {coords, res},
                });
                dispatch({type: ErrorActionTypes.CLEAR_ERROR});
            })
            .catch(error => {
                console.log("Failure, error: " + error);
                dispatch({
                    type: WEATHER_GET_FAILURE,
                    payload: {
                        errorMsg: 'Сервер временно недоступен',
                    },
                    error: true,
                });
                dispatch({
                    type: ErrorActionTypes.APPEAR_ERROR,
                    text: 'Error request for coordinates:' + coords
                });
            })
    }
}

export function requestWeatherDataByCity(city) {
    return dispatch => {
        dispatch({
            type: WEATHER_GET_REQUEST,
        });

        const url = '/weather/weather_by_city';
        httpGet(url, {city})
            .then(res => {
                console.log("Success: " + res);
                dispatch({
                    type: WEATHER_GET_SUCCESS,
                    payload: {city, res},
                });
                dispatch({type: ErrorActionTypes.CLEAR_ERROR});
            })
            .catch(error => {
                console.log("Failure, error: " + error);
                dispatch({
                    type: WEATHER_GET_FAILURE,
                    payload: {
                        errorMsg: 'Сервер временно недоступен',
                    },
                    error: true,
                });
                dispatch({
                    type: ErrorActionTypes.APPEAR_ERROR,
                    text: 'Error request for city: ' + city
                });
            })
    }
}

