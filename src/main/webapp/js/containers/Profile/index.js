import React, {Component} from 'react'
import { connect } from 'react-redux'

import {logoutAction} from '../../modules/AuthActions'

import MenuProfile from '../../components/MenuProfile/MenuProfile'

const mapStateToProps = state => ({
    userName: state.auth.userName
});

const mapDispatchToProps = dispatch => ({
    authLogout: () => dispatch(logoutAction()),
});

const MenuProfileContainer = connect(mapStateToProps, mapDispatchToProps)(MenuProfile);

export {MenuProfileContainer as ProfileMenu};