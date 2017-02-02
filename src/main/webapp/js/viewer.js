var rivetsModelBinder;

var weatherDataModel = {};

$(function($){

    $(document).ready(function() {
        debugger;
        rivetsModelBinder = rivets.bind($('.weather-container'), weatherDataModel);
        requestWeatherData();
    });
});

function viewWeatherData(data) {
    weatherDataModel.data = data;
    weatherDataModel.city = 'London';
}

function requestWeatherData() {
    $.getJSON($('#weatherByCity').val(), {city: 'London'}, function (data) {
        viewWeatherData(data);
    });
}