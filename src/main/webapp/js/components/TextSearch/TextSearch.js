import React, { Component } from 'react';
import PropTypes from 'prop-types'
import _ from 'lodash';

const WAIT_INTERVAL = 8000;
const ENTER_KEY = 13;

export default class TextSearch extends Component {
    componentWillMount() {
        this.timer = null;
    }

    handleChange() {
        clearTimeout(this.timer);
        this.timer = setTimeout(::this.triggerChange, WAIT_INTERVAL);
    }

    handleKeyDown(e) {
        if (e.keyCode === ENTER_KEY) {
            ::this.triggerChange();
        }
    }

    triggerChange() {
        const value  = this.searchInput.value.trim();
        if (!_.isEmpty(value))
            this.props.onSearchTextInput(value);
        this.searchInput.value = '';
    }

    getSearchInputRef = (el) => {this.searchInput = el};

    render() {
        return (
            <div className="input-group custom-search-form">
                <input type="text" className="form-control" placeholder="Search..."
                       ref={this.getSearchInputRef}
                       onChange={::this.handleChange}
                       onKeyDown={::this.handleKeyDown}
                />
                <span className="input-group-btn">
                            <button className="btn btn-default" type="button" onClick={::this.triggerChange}>
                            <i className="fa fa-search"/>
                            </button>
                    </span>
            </div>
        );
    }
}

TextSearch.propTypes = {
    onSearchTextInput: PropTypes.func.isRequired
};