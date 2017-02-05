package weather.viewer.service;

/**
 * User: Gorchakov Dmitriy
 * Date: 05.02.2017.
 */
public class BadRequestException extends RuntimeException {
  public BadRequestException(String code, String message) {
    super(code + ": " + message);
  }

  public BadRequestException(Throwable cause) {
    super(cause);
  }

  public String getMessage() {
    String message = super.getMessage();
    if (message == null && getCause() != null) {
      return getCause().getMessage();
    } else {
      return message;
    }
  }
}
