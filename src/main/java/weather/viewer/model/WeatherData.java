package weather.viewer.model;

/**
 * User: Gorchakov Dmitriy
 * Date: 02.02.2017.
 */
public class WeatherData {
  public Integer temperature;
  public String main;
  public String description;
  public Coords coords;

  public Integer getTemperature() {
    return temperature;
  }

  public void setTemperature(Integer temperature) {
    this.temperature = temperature;
  }

  public String getMain() {
    return main;
  }

  public void setMain(String main) {
    this.main = main;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCoords(Coords coords) {
    this.coords = coords;
  }

  public Coords getCoords() {
    return coords;
  }

  class Coords {
    private float lon;
    private float lat;

    public Coords(float lon, float lat) {
      this.lon = lon;
      this.lat = lat;
    }

    public float getLon() {
      return lon;
    }

    public float getLat() {
      return lat;
    }
  }
}
