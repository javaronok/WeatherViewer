import React, {Component} from 'react'
import PropTypes from 'prop-types'
import { NavDropdown, MenuItem } from 'react-bootstrap';

class MenuProfile extends Component {
    handlerLogout(e) {
        e.preventDefault();
        this.props.authLogout();
    }

    render() {
        return (
          <NavDropdown title={<span><i className="fa fa-user fa-fw"/>&nbsp;{this.props.userName} </span>} id='navDropdown4'>
            <MenuItem eventKey="4" onClick={::this.handlerLogout}>
                <span> <i className="fa fa-sign-out fa-fw"/> Logout </span>
            </MenuItem>
          </NavDropdown>
        )
    }
}

MenuProfile.propTypes = {
    userName: PropTypes.string.isRequired,
    authLogout: PropTypes.func.isRequired
};

export default MenuProfile