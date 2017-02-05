package weather.viewer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import weather.viewer.model.ErrorMessage;
import weather.viewer.model.WeatherData;
import weather.viewer.util.SecurityUtils;
import weather.viewer.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

/**
 * User: Gorchakov Dmitriy
 * Date: 03.02.2017.
 */
public class WeatherDataResponseHandler implements HttpResponseHandler<WeatherData> {

  Logger LOG =  LoggerFactory.getLogger(WeatherDataResponseHandler.class);

  private final ObjectMapper mapper;
  private WeatherData result;
  private ErrorMessage errorMessage;

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
          LOG.info("[RESPONSE] data :: " + json);
        }
      } catch (IOException e) {
        throw new BadRequestException(e);
      }
    }
  }

  @Override
  public void handleError(HttpResponse response) throws IOException {
    HttpEntity entity = response.getEntity();
    if (entity != null) {
      try {
        try (InputStream content = entity.getContent()) {
          String json = StreamUtils.readToString(content);
          this.errorMessage = mapper.readValue(json, ErrorMessage.class);
          LOG.info("[RESPONSE] data :: " + json);
        }
      } catch (IOException e) {
        throw new BadRequestException(e);
      }
    }
  }

  @Override
  public WeatherData getResult() {
    return this.result;
  }

  @Override
  public ErrorMessage getErrorMessage() {
    return this.errorMessage;
  }
}
