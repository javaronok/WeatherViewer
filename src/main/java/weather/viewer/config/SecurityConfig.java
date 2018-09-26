package weather.viewer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import weather.viewer.security.ForwardedAuthenticationSuccessHandler;

/**
 * User: Gorchakov Dmitriy
 * Date: 05.02.2017.
 */
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user1").password("user1").roles("USER");
    auth.inMemoryAuthentication().withUser("user2").password("user2").roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeRequests().anyRequest().permitAll()
            .and()
            .httpBasic()
            .and().formLogin()
            .loginProcessingUrl("/j_security_login")
            .usernameParameter("j_username")
            .passwordParameter("j_password")
            .successHandler(new SimpleUrlAuthenticationSuccessHandler())
            .failureHandler(new SimpleUrlAuthenticationFailureHandler())
            .and()
            .logout()
            .logoutUrl("/j_security_logout");

  }
}