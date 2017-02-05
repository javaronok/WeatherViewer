package weather.viewer.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weather.viewer.model.ErrorMessage;
import weather.viewer.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: Gorchakov Dmitriy
 * Date: 03.02.2017.
 */
public class TextDataResultHandler implements HttpResponseHandler {

  Logger LOG =  LoggerFactory.getLogger(TextDataResultHandler.class);

  @Override
  public void handleRequest(HttpRequest request) {
    //nothing
  }

  @Override
  public void handleResponse(HttpResponse response) throws IOException {
    HttpEntity entity = response.getEntity();
    if (entity != null) {
      LOG.debug("Response content length: " + entity.getContentLength());
      try {
        try (InputStream content = entity.getContent()) {
          LOG.debug(StreamUtils.readToString(content));
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
  public ErrorMessage getErrorMessage() {
    return null;
  }
}
