package weather.viewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertyResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import weather.viewer.model.WeatherData;
import weather.viewer.service.*;

/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
@RestController
@RequestMapping("weather")
public class WeatherController {

  private static final String WEATHER_BY_CITY = "weather_by_city";
  private static final String WEATHER_BY_COORDS = "weather_by_coords";

  @Autowired private ClientExecutorService clientService;
  @Autowired private PropertyResolver resolver;
  @Autowired private MappingJackson2HttpMessageConverter converter;

  @ResponseBody
  @RequestMapping(value = WEATHER_BY_CITY, method = RequestMethod.GET, produces = "application/json")
  public WeatherData getWeatherByCityName(@RequestParam("city") String city) {
    String key = resolver.getProperty("weather.key");
    ClientCommand command = new GetWeatherByCity(key, city);
    HttpResponseHandler<WeatherData> resultHandler = new WeatherDataResponseHandler(converter.getObjectMapper());
    int code = clientService.invoke(command, resultHandler);
    return resultHandler.getResult();
  }

  @ResponseBody
  @RequestMapping(value = WEATHER_BY_COORDS, method = RequestMethod.GET, produces = "application/json")
  public WeatherData getWeatherByCoords(@RequestParam("lat") Float lat, @RequestParam("lon") Float lon) {
    String key = resolver.getProperty("weather.key");
    ClientCommand command = new GetWeatherByCoords(key, lon, lat);
    HttpResponseHandler<WeatherData> resultHandler = new WeatherDataResponseHandler(converter.getObjectMapper());
    int code = clientService.invoke(command, resultHandler);
    return resultHandler.getResult();
  }
}
