package weather.viewer.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import weather.viewer.model.WeatherData;
import weather.viewer.service.ClientExecutorService;
import weather.viewer.service.StreamUtils;
import weather.viewer.service.GetWeatherByCity;
import weather.viewer.service.HttpResponseHandler;

import java.io.IOException;
import java.io.InputStream;

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

  @ResponseBody
  @RequestMapping(value = WEATHER_BY_CITY, method = RequestMethod.GET, produces = "application/json")
  public WeatherData getWeatherByCityName(@RequestParam("city") String city) {
    WeatherData data = new WeatherData();
    data.setTemperature(-2);
    data.setMain("Cool");
    data.setDescription("Light cool");

    GetWeatherByCity command = new GetWeatherByCity("a1c72c6a7af2411a150ec7d6a51689c1", city);
    clientService.invoke(command, new HttpResponseHandler() {
      @Override
      public void handleRequest(HttpRequest request) {

      }

      @Override
      public void handleResponse(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
          System.out.println("Response content length: " + entity.getContentLength());

          try {
            try (InputStream content = entity.getContent()) {
              System.out.println(StreamUtils.readToString(content));
            }
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      }

      @Override
      public void handleError(HttpResponse response) throws IOException {

      }

      @Override
      public Object getResult() {
        return null;
      }

      @Override
      public String getErrorText() {
        return null;
      }
    });

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
