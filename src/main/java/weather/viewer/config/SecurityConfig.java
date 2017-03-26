package weather.viewer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
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
            .and().formLogin()
            .loginProcessingUrl("/j_security_login")
            .usernameParameter("j_username")
            .passwordParameter("j_password")
            .loginPage("/login").permitAll()
            .defaultSuccessUrl("/", false)
            .successHandler(new ForwardedAuthenticationSuccessHandler()) // For the microservice design
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .logoutUrl("/j_security_logout");

  }
}