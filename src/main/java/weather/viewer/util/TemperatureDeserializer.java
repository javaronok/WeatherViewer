package weather.viewer.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * User: Gorchakov Dmitriy
 * Date: 03.02.2017.
 */
public class TemperatureDeserializer extends JsonDeserializer<Float> {
  @Override
  public Float deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return new BigDecimal(jp.getFloatValue() - 273.16).setScale(1, RoundingMode.UP).floatValue(); // Kelvin to Cel with around
  }
}
