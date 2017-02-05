package weather.viewer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Gorchakov Dmitriy
 * Date: 05.02.2017.
 */
public class ErrorMessage {
  @JsonProperty("cod")
  private String code;

  @JsonProperty("message")
  private String message;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
