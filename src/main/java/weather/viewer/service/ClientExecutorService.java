package weather.viewer.service;

/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
public interface ClientExecutorService {
  public int invoke(ClientCommand command, HttpResponseHandler handler);
}
