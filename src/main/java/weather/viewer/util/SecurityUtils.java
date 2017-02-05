package weather.viewer.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

/**
 * @author Emelyanov (05.02.2017)
 */

public final class SecurityUtils {

  public static Principal getCurrentPrincipal() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth != null ? (Principal) auth.getPrincipal() : null;
  }

  public static String getUserLogin() {
    return getUserLogin(getCurrentPrincipal());

  }
  public static String getUserLogin(Principal principal) {
    return principal != null ? principal.getName() : "anonimous";
  }

}
