package weather.viewer.service;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
public class GetWeatherByCity implements ClientCommand {
  private String key;
  private String city;

  public GetWeatherByCity(String key, String city) {
    this.key = key;
    this.city = city;
  }

  @Override
  public String getUrl() {
    return "http://api.openweathermap.org/data/2.5/weather";
  }

  @Override
  public Map<String, String> getSearchParams() {
    Map<String, String> params = new HashMap<>();
    params.put("q", city);
    params.put("appid", key);
    return params;
  }
}
