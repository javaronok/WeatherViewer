import React from 'react'
import { connect } from 'react-redux'
import PropTypes from 'prop-types'

import Layout from './containers/Layout/Layout'
import ViewerContainer from './containers/Viewer'
import Spinner from './components/Spinner/Spinner'

class App extends React.Component {
    render() {
        return (
            <Layout isAuth={this.props.isAuth}>
                {this.props.loading && <Spinner/>}
                <ViewerContainer/>
            </Layout>
        )
    }
}

App.propTypes = {
    loading: PropTypes.bool,
    isAuth: PropTypes.bool
};

const mapStateToProps = state => ({
    loading: state.request.loading,
    isAuth: state.auth.isAuth
});

export default connect(mapStateToProps)(App)