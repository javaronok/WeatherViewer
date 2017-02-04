package weather.viewer.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import weather.viewer.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: Gorchakov Dmitriy
 * Date: 03.02.2017.
 */
public class TextDataResultHandler implements HttpResponseHandler {
  @Override
  public void handleRequest(HttpRequest request) {
    //nothing
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
}
