package com.exemple.api.config.security;

import com.exemple.api.config.security.filter.JWTFilter;
import com.exemple.api.common.helper.JWTHelper;
import com.exemple.api.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JWTHelper jwtHelper;
    private final UserService userService;

    public DevSecurityConfiguration(JWTHelper jwtHelper, UserService userService) {
        this.jwtHelper = jwtHelper;
        this.userService = userService;
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //general:
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();

        //access management:
        http.authorizeRequests().anyRequest().permitAll();

        //filters
        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private JWTFilter jwtFilter() {
        return new JWTFilter(jwtHelper, userService);
    }
}
