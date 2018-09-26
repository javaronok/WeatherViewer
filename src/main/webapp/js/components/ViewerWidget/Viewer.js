'use strict';

import React from 'react'
import PropTypes from 'prop-types'
import { Panel } from 'react-bootstrap'
import './viewer.css'

const Viewer = ({data}) => {
    return (
        <Panel bsStyle="primary">
            <Panel.Heading>
                <Panel.Title>Weather in {data.name}</Panel.Title>
            </Panel.Heading>
            <Panel.Body>
                <div className="row">
                                    <span className="col col-2 weather-item clearfix">
                                        <img alt={`Weather in ${data.name}`}
                                             src={`http://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/${data.weather.icon}.png`}
                                             width="128" height="128"
                                        />
                                    </span>
                    <span className="col col-2 weather-item clearfix">
                                      <h1>{data.main.temp}<span>°</span></h1>
                                    </span>
                    <span className="col col-2 col-lg-offset-1 weather-item clearfix">
                                        <div className="row">
                                            <span><strong>{data.weather.main}</strong></span>
                                        </div>
                                        <div className="row">
                                            <span>{data.weather.description}</span>
                                        </div>
                                    </span>
                </div>
            </Panel.Body>
            <Panel.Footer>Open weather map</Panel.Footer>
        </Panel>
    )
};

Viewer.propTypes = {
    data: PropTypes.object.isRequired
};

export default Viewer