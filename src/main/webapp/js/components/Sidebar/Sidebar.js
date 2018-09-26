import React, { Component } from 'react'
import PropTypes from 'prop-types'

import SearchBar from '../../containers/SearchBar'
import NavMenuItem from './NavMenuItem'

const Sidebar = ({isAuth}) => {
    return (
        <div className="navbar-default sidebar" style={{marginLeft: '-20px'}} role="navigation">
            <div className="sidebar-nav navbar-collapse collapse">
                <ul className="nav in" id="side-menu">
                    <li className="sidebar-search">
                        <SearchBar/>
                    </li>
                    <NavMenuItem path="/viewer" caption="Weather view" icon="fa-dashboard"/>
                    {!isAuth && <NavMenuItem path="/login" caption="SignIn" icon="fa-user"/>}
                </ul>
            </div>
        </div>
    );
};

Sidebar.propTypes = {
    isAuth: PropTypes.bool
};

export default Sidebar;
