package com.geek.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * we use the @Profile annotation to specify that this component/bean should only be activated in the "prod" profile.
 */
@Configuration
@Profile("prod")
public class ProjectSecurityProdConfig {

    /**
     * The below method is used to create a SecurityFilterChain bean which is used to configure web based security for the application.
     * The HttpSecurity object is used to configure the security filter chain.
     * The authorizeHttpRequests method is used to configure the authorization of HTTP requests.
     * The requestMatchers method is used to specify the requests that are used to configure the authorization.
     * The authenticated method is used to specify that the requests require authentication.
     * The permitAll method is used to specify that all requests are permitted.
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrfConfigurer -> csrfConfigurer.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contact", "/error", "/register").permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    /**
     * We can refer PasswordEncoderFactories.createDelegatingPasswordEncoder() to know the list of supported encoders
     * along with the default encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * CompromisedPasswordChecker is used to check if the password is compromised
     * This bean will enforce the user to use a strong password
     */
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}

