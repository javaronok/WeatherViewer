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

                /*
                 В объекте position изложена подробная информация
                 о позиции устройства:

                 position = {
                 coords: {
                 latitude - Широта.
                 longitude - Долгота.
                 altitude - Высота в метрах над уровнем моря.
                 accuracy - Погрешность в метрах.
                 altitudeAccuracy - Погрешность высоты над уровнем моря в метрах.
                 heading - Направление устройства по отношению к северу.
                 speed - Скорость в метрах в секунду.
                 }
                 timestamp - Время извлечения информации.
                 }
                 */

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

                alert(error);
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
    });
}

function requestByCity(city) {
    $.getJSON($('#weatherByCity').val(), {city: city}, function (data) {
        viewWeatherData(data);
    });
}