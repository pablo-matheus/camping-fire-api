package br.com.campingfire.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(AuthenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "campings").permitAll()
                .antMatchers(HttpMethod.GET, "campings/*").permitAll()
                .antMatchers(HttpMethod.GET, "images").permitAll()
                .antMatchers(HttpMethod.GET, "images/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
