package weather.viewer.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.SimpleType;
import weather.viewer.model.WeatherData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Gorchakov Dmitriy
 * Date: 03.02.2017.
 */
public class WeatherInfoDeserializer extends JsonDeserializer<WeatherData.WeatherInfo> implements ContextualDeserializer {
  CollectionDeserializer delegate;

  protected WeatherInfoDeserializer(CollectionDeserializer delegate) {
    this.delegate = delegate;
  }

  public WeatherInfoDeserializer() {}

  @Override
  public WeatherData.WeatherInfo deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    Collection<Object> res = delegate.deserialize(jp, ctxt);
    return !res.isEmpty() ? (WeatherData.WeatherInfo) res.iterator().next() : null;
  }

  @Override
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    JavaType type = SimpleType.construct(WeatherData.WeatherInfo.class);
    JsonDeserializer<Object> valueDeser = ctxt.findContextualValueDeserializer(type, property);

    CollectionType collectionType = CollectionType.construct(ArrayList.class, type);

    BeanDescription beanDesc = ctxt.getConfig().introspect(collectionType);

    CreatorCollector collector = new CreatorCollector(beanDesc, false);
    ValueInstantiator vi = collector.constructValueInstantiator(ctxt.getConfig());

    CollectionDeserializer delegate = new CollectionDeserializer(collectionType, valueDeser, null, vi);
    return new WeatherInfoDeserializer(delegate);
  }
}
