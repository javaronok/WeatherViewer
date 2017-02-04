package weather.viewer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import weather.viewer.model.WeatherData;
import weather.viewer.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: Gorchakov Dmitriy
 * Date: 03.02.2017.
 */
public class WeatherDataResponseHandler implements HttpResponseHandler<WeatherData> {
  private final ObjectMapper mapper;
  private WeatherData result;

  public WeatherDataResponseHandler(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public void handleRequest(HttpRequest request) {
    //nothing
  }

  @Override
  public void handleResponse(HttpResponse response) throws IOException {
    HttpEntity entity = response.getEntity();
    if (entity != null) {
      try {
        try (InputStream content = entity.getContent()) {
          String json = StreamUtils.readToString(content);
          this.result = mapper.readValue(json, WeatherData.class);
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
  public WeatherData getResult() {
    return this.result;
  }

  @Override
  public String getErrorText() {
    return null;
  }
}
