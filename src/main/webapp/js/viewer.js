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
        initSearchAction();
        requestWeatherData();
    });
});

function viewWeatherData(data) {
    weatherDataModel.error = null;
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
            function(error) {
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
                handleError({responseText: error.message});
            }
        );
    } else {
        // Геолокация не доступна
        handleError({responseText: 'Geolocation is not supported by your browser'});
    }
}

function requestByCoords(lat, lon) {
    $.getJSON($('#weatherByCoords').val(), {lat: lat, lon: lon}, function (data) {
        viewWeatherData(data);
    }).fail(function(error) {
        handleError(error);
    });
}

function requestByCity(city) {
    $.getJSON($('#weatherByCity').val(), {city: city}, function (data) {
        viewWeatherData(data);
    }).fail(function(error) {
        handleError(error);
    }).always(function() {
        $('input.search-input').val('');
    });
}

function initSearchAction() {
    $('input.search-input').on('keydown', function(e) {
        if (e.which == 13) { // Enter pressed
            e.preventDefault();
            requestByCity($('input.search-input').val());
        }
    });

    $('button.search-button').on('click', function(e) {
        e.preventDefault();
        requestByCity($('input.search-input').val());
    });
}

function handleError(error) {
    weatherDataModel.data = null;
    weatherDataModel.error = {errorText: error.responseText};
    console.log( "Request Failed: " + error.responseText );
}