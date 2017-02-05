package weather.viewer.service;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import weather.viewer.model.ErrorMessage;

import java.io.IOException;

/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
public interface HttpResponseHandler<T> {
  void handleRequest(HttpRequest request);
  void handleResponse(HttpResponse response) throws IOException;
  void handleError(HttpResponse response) throws IOException;
  T getResult();
  ErrorMessage getErrorMessage();
}

