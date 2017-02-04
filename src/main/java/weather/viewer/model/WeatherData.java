package weather.viewer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import weather.viewer.util.TemperatureDeserializer;
import weather.viewer.util.WeatherInfoDeserializer;


/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
public class WeatherData {
  @JsonProperty("main")
  public MainData main;

  @JsonProperty("weather")
  @JsonDeserialize(using = WeatherInfoDeserializer.class)
  public WeatherInfo currentInfo;

  @JsonProperty("coord")
  public Coords coords;

  @JsonProperty("name")
  public String location;

  public MainData getMain() {
    return main;
  }

  public void setMain(MainData main) {
    this.main = main;
  }

  public void setCoords(Coords coords) {
    this.coords = coords;
  }

  public Coords getCoords() {
    return coords;
  }

  public WeatherInfo getCurrentInfo() {
    return currentInfo;
  }

  public void setCurrentInfo(WeatherInfo currentInfo) {
    this.currentInfo = currentInfo;
  }

  public static class Coords {
    private float lon;
    private float lat;

    public void setLon(float lon) {
      this.lon = lon;
    }

    public void setLat(float lat) {
      this.lat = lat;
    }

    public float getLon() {
      return lon;
    }

    public float getLat() {
      return lat;
    }
  }

  public static class MainData {
    @JsonProperty("temp")
    @JsonDeserialize(using = TemperatureDeserializer.class)
    private Float temperature;

    public Float getTemperature() {
      return temperature;
    }

    public void setTemperature(Float temperature) {
      this.temperature = temperature;
    }
  }

  public static class WeatherInfo {
    @JsonProperty("main")
    private String info;

    private String description;

    @JsonProperty("icon")
    private String iconSuffix;

    public WeatherInfo() {
    }

    public String getInfo() {
      return info;
    }

    public void setInfo(String info) {
      this.info = info;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getIconSuffix() {
      return iconSuffix;
    }

    public void setIconSuffix(String iconSuffix) {
      this.iconSuffix = iconSuffix;
    }
  }
}
