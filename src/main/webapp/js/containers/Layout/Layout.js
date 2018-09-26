import React, {Component} from 'react'
import Header from '../../components/Header/Header'

class Layout extends Component {
    render() {
        return (
            <div className='wrapper'>
                <Header isAuth={this.props.isAuth}/>
                <div id="page-wrapper" className="page-wrapper">
                        {this.props.children}
                </div>
                {/*<Footer />*/}
            </div>
        )
    }
}

export default Layout