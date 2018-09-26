import React from 'react';
import ReactDOM from 'react-dom';
import { Router } from 'react-router-dom'
import { Provider } from 'react-redux'
import { createStore, combineReducers, applyMiddleware, compose } from 'redux'
import thunk from 'redux-thunk'
import { createLogger } from 'redux-logger'

import authReducer from './modules/AuthReducer'
import requestReducer from './modules/RequestReducer'
import errorReducer from './modules/ErrorReducer'
import viewerReducer from './containers/Viewer/reducer'

import RootRoutes from './routes'
import history from './history'

const middleware = [thunk];
if (process.env.NODE_ENV !== 'production') {
    middleware.push(createLogger())
}

export const rootReducer = combineReducers({
        error: errorReducer,
        auth: authReducer,
        viewer: viewerReducer,
        request: requestReducer,
    }
);

const store = createStore(rootReducer, applyMiddleware(...middleware));

ReactDOM.render(
    <Provider store={store}>
        <Router history={history}>
            <RootRoutes/>
        </Router>
    </Provider>,
    document.getElementById('root')
);