package weather.viewer.service;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
@Service
public class ClientExecutorServiceImpl implements ClientExecutorService {
  @Override
  public int invoke(ClientCommand command, HttpResponseHandler handler) {
    CloseableHttpClient client = HttpClientBuilder.create().build();
    try {
      HttpGet httpGet = createOperation(command);
      handler.handleRequest(httpGet);
      HttpResponse response = client.execute(httpGet);
      int status = response.getStatusLine().getStatusCode();
      if (HttpStatus.SC_OK == status)
        handler.handleResponse(response);
      else
        handler.handleError(response);
      return status;
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        client.close();
      } catch (IOException e) {
        //nothing
      }
    }
  }

  private HttpGet createOperation(ClientCommand command) throws URISyntaxException {
    URIBuilder uriBuilder = new URIBuilder(command.getUrl());

    Map<String, String> params = command.getSearchParams();
    if (!params.isEmpty()) {
      for (Map.Entry<String, String> e : params.entrySet()) {
        uriBuilder.addParameter(e.getKey(), e.getValue());
      }
    }

    return new HttpGet(uriBuilder.build());
  }

  private HttpGet createOperationText(ClientCommand command) {
    StringBuilder sb = new StringBuilder(command.getUrl());

    Map<String, String> params = command.getSearchParams();
    if (!params.isEmpty()) {
      StringBuilder clientParams = new StringBuilder();
      for (Map.Entry<String, String> e : params.entrySet()) {
        clientParams.append("&").append(e.getKey()).append("=").append(e.getValue());
      }
      sb.append("?").append(clientParams.substring(1));
    }

    return new HttpGet(sb.toString());
  }


}
