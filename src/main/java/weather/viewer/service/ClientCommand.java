package weather.viewer.service;

import java.util.Map;

/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
public interface ClientCommand {
  public String getUrl();
  public Map<String, String> getSearchParams();
}
