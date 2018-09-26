import React from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import _ from 'lodash'
import Viewer from '../../components/ViewerWidget/Viewer'
import { PageHeader, Alert } from 'react-bootstrap'
import {appearError, clearError} from '../../modules/ErrorActions'
import {requestWeatherDataByCoords} from '../../modules/RequestActions'

class ViewerContainer extends React.Component {

    initGeoLocation() {
        return new Promise((resolve, reject) => {
            if (navigator.geolocation) {
                // Геолокация доступна
                navigator.geolocation.getCurrentPosition(
                    // Функция обратного вызова при успешном извлечении локации
                    (position) => {
                        //requestByCoords(position.coords.latitude, position.coords.longitude);
                        resolve(position)
                    },

                    // Функция обратного вызова при неуспешном извлечении локации
                    (error) => {
                        /*
                         При неудаче, будет доступен объект error:

                         error = {
                         code - Тип ошибки
                         1 - PERMISSION_DENIED
                         2 - POSITION_UNAVAILABLE
                         3 - TIMEOUT

                         message - Детальная информация.
                         }
                         */
                        reject({responseText: error.message});
                    }
                );
            } else {
                // Геолокация не доступна
                reject({responseText: 'Geolocation is not supported by your browser'});
            }
        });
    };

    handleError(err) {
        console.warn(err.responseText);
        this.props.throwError(err);
    };

    handleDismiss() {
        this.props.clearError();
    };

    async componentDidMount() {
        try {
            let data = await ::this.initGeoLocation();
            console.log(data);
            this.props.requestByCoords(data);
        } catch(error) {
            ::this.handleError(error);
        }
    }

    render() {
        let warning = this.props.error &&
            <Alert bsStyle="danger" onDismiss={::this.handleDismiss}>
                <strong>Error:</strong> {this.props.error}
            </Alert>;

        let {data} = this.props;

        return (
            <div className="container-fluid weather-container">
                <div className="row">
                    <div className="col-lg-12">
                        <PageHeader>Weather view</PageHeader>
                    </div>
                </div>

                {warning}

                <div className="row">
                    <div className="col-lg-6">
                        {data && !_.isEmpty(data) && <Viewer data={data}/>}
                    </div>
                </div>
            </div>
        )
    }
}

ViewerContainer.propTypes = {
    requestByCoords: PropTypes.func.isRequired,
    throwError: PropTypes.func.isRequired,
    clearError: PropTypes.func.isRequired,
    error: PropTypes.string
};


const mapStateToProps = state => ({
    error: state.error.text,
    data: state.viewer.data
});

const mapDispatchToProps = dispatch => ({
    requestByCoords: coords => dispatch(requestWeatherDataByCoords(coords)),
    throwError: error => dispatch(appearError(error)),
    clearError: () => dispatch(clearError()),
});

export default connect(mapStateToProps, mapDispatchToProps)(ViewerContainer);