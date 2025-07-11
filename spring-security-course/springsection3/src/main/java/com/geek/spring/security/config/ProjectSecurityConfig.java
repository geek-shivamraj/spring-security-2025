package com.geek.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    /**
     * The below method is a Spring Bean, which is a factory method that creates a SecurityFilterChain.
     * SecurityFilterChain is the main entry point for configuring web based security for an application.
     * The HttpSecurity object is used to configure the security filter chain.
     * The configure method is overridden to configure the security filter chain.
     * The authorizeHttpRequests method is used to configure the authorization of HTTP requests.
     * The requestMatchers method is used to specify the requests that are used to configure the authorization.
     * The authenticated method is used to specify that the requests require authentication.
     * The permitAll method is used to specify that all requests are permitted.
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());

        // Here both formLogin and httpBasic are enabled
        http.authorizeHttpRequests(requests -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contact", "/error").permitAll())
                .formLogin(httpFormLoginConfigurer -> httpFormLoginConfigurer.disable())
                //.formLogin(withDefaults())
                //.httpBasic(httpBasicConfigurer -> httpBasicConfigurer.disable())
                .httpBasic(withDefaults());

        return http.build();
    }
}
