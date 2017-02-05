package weather.viewer.service;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import weather.viewer.model.ErrorMessage;
import weather.viewer.model.WeatherData;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class ClientTaskExecutorService {
    @Autowired private MappingJackson2HttpMessageConverter converter;
    @Autowired private ClientExecutorService clientService;
    @Autowired private ThreadPoolTaskExecutor taskExecutor;

    public WeatherData invokeClient(ClientCommand command) {
        HttpResponseHandler<WeatherData> resultHandler = new WeatherDataResponseHandler(converter.getObjectMapper());

        Future<WeatherData> f = taskExecutor.submit(() -> {
            int code = clientService.invoke(command, resultHandler);
            if (HttpStatus.SC_OK == code) {
                return resultHandler.getResult();
            } else {
                ErrorMessage err = resultHandler.getErrorMessage();
                throw new BadRequestException(err.getCode(), err.getMessage());
            }
        });

        try {
            return f.get();
        } catch (InterruptedException e) {
            throw new BadRequestException(e);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof BadRequestException) {
                BadRequestException ex = (BadRequestException) e.getCause();
                throw ex;
            }
            throw new BadRequestException(e);
        }
    }
}
