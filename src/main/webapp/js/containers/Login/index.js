import { connect } from 'react-redux'
import {loginAction} from '../../modules/AuthActions'
import Login from '../../components/Login/Login'

const mapStateToProps = state => ({});

const mapDispatchToProps = dispatch => ({
    authLogin: auth => dispatch(loginAction(auth)),
});

export default connect(mapStateToProps, mapDispatchToProps)(Login)