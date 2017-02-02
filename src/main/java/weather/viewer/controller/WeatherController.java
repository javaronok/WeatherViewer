package weather.viewer.controller;

import org.springframework.web.bind.annotation.*;
import weather.viewer.model.WeatherData;

/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
@RestController
@RequestMapping("weather")
public class WeatherController {

  private static final String WEATHER_BY_CITY = "weather_by_city";
  private static final String WEATHER_BY_COORDS = "weather_by_coords";

  @ResponseBody
  @RequestMapping(value = WEATHER_BY_CITY, method = RequestMethod.GET, produces = "application/json")
  public WeatherData getWeatherByCityName(@RequestParam("city") String city) {
    WeatherData data = new WeatherData();
    data.setTemperature(-2);
    data.setMain("Cool");
    data.setDescription("Light cool");
    return data;
  }

  @ResponseBody
  @RequestMapping(value = WEATHER_BY_COORDS, method = RequestMethod.GET, produces = "application/json")
  public WeatherData getWeatherByCoords() {
    WeatherData data = new WeatherData();
    data.setTemperature(-5);
    data.setDescription("Cold");
    return data;

  }
}
