package weather.viewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertyResolver;
import org.springframework.web.bind.annotation.*;
import weather.viewer.model.WeatherData;
import weather.viewer.service.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
@RestController
@RequestMapping("weather")
public class WeatherController {

  private static final String WEATHER_BY_CITY = "weather_by_city";
  private static final String WEATHER_BY_COORDS = "weather_by_coords";

  @Autowired private PropertyResolver resolver;
  @Autowired private ClientTaskExecutorService taskExecutor;

  @ResponseBody
  @RequestMapping(value = WEATHER_BY_CITY, method = RequestMethod.GET, produces = "application/json")
  public WeatherData getWeatherByCityName(@RequestParam("city") String city, Principal principal) {
    String key = resolver.getProperty("weather.key");
    ClientCommand command = new GetWeatherByCity(key, city);
    return taskExecutor.invokeClient(command);
  }

  @ResponseBody
  @RequestMapping(value = WEATHER_BY_COORDS, method = RequestMethod.GET, produces = "application/json")
  public WeatherData getWeatherByCoords(@RequestParam("lat") Float lat, @RequestParam("lon") Float lon, Principal principal) {
    String key = resolver.getProperty("weather.key");
    ClientCommand command = new GetWeatherByCoords(key, lon, lat);
    return taskExecutor.invokeClient(command);
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public String handleException(Exception e, HttpServletResponse response) {
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    return e.getMessage();
  }
}
