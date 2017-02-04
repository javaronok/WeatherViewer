package weather.viewer.service;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Gorchakov Dmitriy
 * Date: 04.02.2017.
 */
public class GetWeatherByCoords implements ClientCommand {
  private String key;
  private Float lon;
  private Float lat;

  public GetWeatherByCoords(String key, Float lon, Float lat) {
    this.key = key;
    this.lon = lon;
    this.lat = lat;
  }

  @Override
  public String getUrl() {
    return "http://api.openweathermap.org/data/2.5/weather";
  }

  @Override
  public Map<String, String> getSearchParams() {
    Map<String, String> params = new HashMap<>();
    params.put("lon", String.valueOf(lon));
    params.put("lat", String.valueOf(lat));
    params.put("appid", key);
    return params;
  }
}
