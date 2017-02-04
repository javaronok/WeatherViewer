var rivetsModelBinder;

var weatherDataModel = {};

rivets.binders.wsrc = function(el, value) {
    var href = "http://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/" + value + ".png";
    el.setAttribute("src", href);
};

rivets.binders.walt = function(el, value) {
    var alt = "Weather in" + value;
    el.setAttribute("alt", alt);
};

$(function($){

    $(document).ajaxStart(function(){
        $("#ajax_loader").show();
    });
    $(document).ajaxStop(function(){
        $("#ajax_loader").hide();
    });

    $(document).ready(function() {
        rivetsModelBinder = rivets.bind($('.weather-container'), weatherDataModel);
        requestWeatherData();
    });
});

function viewWeatherData(data) {
    weatherDataModel.data = data;
}

function requestWeatherData() {
    if (navigator.geolocation) {
        // Геолокация доступна
        navigator.geolocation.getCurrentPosition(
            // Функция обратного вызова при успешном извлечении локации
            function(position) {
                requestByCoords(position.coords.latitude, position.coords.longitude);
            },

            // Функция обратного вызова при неуспешном извлечении локации
            function(error){
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

                alert(error); // todo: убрать
            }
        );
    }
    else {
        // Геолокация не доступна
        requestByCity('London');
    }
}

function requestByCoords(lat, lon) {
    $.getJSON($('#weatherByCoords').val(), {lat: lat, lon: lon}, function (data) {
        viewWeatherData(data);
    }).fail(function( jqxhr, textStatus, error ) {
        console.log( "Request Failed: " + jqxhr.responseText ); // todo: добавить нотификацию
    });
}

function requestByCity(city) {
    $.getJSON($('#weatherByCity').val(), {city: city}, function (data) {
        viewWeatherData(data);
    }).fail(function( jqxhr, textStatus, error ) {
        console.log( "Request Failed: " + jqxhr.responseText );  // todo: добавить нотификацию
    });
}