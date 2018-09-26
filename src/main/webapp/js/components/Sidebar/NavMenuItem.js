import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import PropTypes from 'prop-types'

class NavMenuItem extends Component {
    render() {
        const {path, caption, icon} = this.props;
        const menuClassName = "fa fa-fw" + (icon && ` ${icon}`);
        return (
            <li>
                <Link to={path}>
                    <i className={menuClassName}/> &nbsp;{caption}
                </Link>
            </li>
        )
    }
}

NavMenuItem.propTypes = {
    path: PropTypes.string.isRequired,
    caption: PropTypes.string.isRequired,
    icon: PropTypes.string
};

export default NavMenuItem;