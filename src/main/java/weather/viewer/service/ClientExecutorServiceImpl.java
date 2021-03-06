package weather.viewer.service;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
@Service
public class ClientExecutorServiceImpl implements ClientExecutorService {

  Logger LOG =  LoggerFactory.getLogger(ClientExecutorServiceImpl.class);

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
      LOG.error("Request exception", e);
      throw new BadRequestException(e);
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

}
