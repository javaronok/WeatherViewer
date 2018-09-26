import { connect } from 'react-redux'
import TextSearch from '../../components/TextSearch/TextSearch'
import {requestWeatherDataByCity} from '../../modules/RequestActions'

const mapStateToProps = state => ({});

const mapDispatchToProps = dispatch => ({
    onSearchTextInput: city => dispatch(requestWeatherDataByCity(city)),
});

export default connect(mapStateToProps, mapDispatchToProps)(TextSearch);