package weather.viewer.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Gorchakov Dmitriy
 * Date: 26.03.2017.
 */
public class ForwardedAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  public static final String X_FORWARDED_HOST = "x-forwarded-host"; 
  public static final String X_FORWARDED_PROTO = "x-forwarded-proto"; 
  public static final String X_FORWARDED_PORT = "x-forwarded-port"; 
  
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
    String forwardProto = request.getHeader(X_FORWARDED_PROTO);
    String forwardHost = request.getHeader(X_FORWARDED_HOST);
    String forwardPort = request.getHeader(X_FORWARDED_PORT);

    if (forwardProto != null && forwardHost != null && forwardPort != null) {
      String ctxPath = request.getContextPath();
      String redirectUrl = determineTargetUrl(request, response);
      String targetUrl = String.format("%s://%s:%s%s%s", forwardProto, forwardHost, forwardPort, ctxPath, redirectUrl);
      getRedirectStrategy().sendRedirect(request, response, targetUrl);
    } else {
      super.onAuthenticationSuccess(request, response, authentication);
    }
  }
}
