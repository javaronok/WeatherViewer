import React from 'react'
import { Switch, Route, Redirect } from 'react-router-dom';
import App from './app'
import Login from './containers/Login'

const RootRoutes = () => (
    <Switch>
        <Redirect exact from="/" to="/viewer"/>
        <Route exact path='/viewer' component={App} />
        <Route path='/login' component={Login} />
        <Route component={App}/>
    </Switch>
);

export default RootRoutes;
