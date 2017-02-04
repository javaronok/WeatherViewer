import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertyResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import weather.viewer.config.AppWebMVCConfig;
import weather.viewer.model.WeatherData;
import weather.viewer.service.*;

/**
 * User: Gorchakov Dmitriy
 * Date: 03.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppWebMVCConfig.class})
@WebAppConfiguration
public class WeatherGetDataTest {

  @Autowired private ClientExecutorService clientService;
  @Autowired private PropertyResolver resolver;
  @Autowired private MappingJackson2HttpMessageConverter converter;

  @Test
  public void testGetWeatherByCity() {
    String key = resolver.getProperty("weather.key");
    ClientCommand command = new GetWeatherByCity(key, "London");
    HttpResponseHandler<WeatherData> resultHandler = new WeatherDataResponseHandler(converter.getObjectMapper());
    int code = clientService.invoke(command, resultHandler);
    WeatherData result = resultHandler.getResult();
  }

  @Test
  public void testGetWeatherByCoords() {
    String key = resolver.getProperty("weather.key");
    ClientCommand command = new GetWeatherByCoords(key, new Float(-0.13), new Float(51.51));
    HttpResponseHandler<WeatherData> resultHandler = new WeatherDataResponseHandler(converter.getObjectMapper());
    int code = clientService.invoke(command, resultHandler);
    WeatherData result = resultHandler.getResult();
  }

}
