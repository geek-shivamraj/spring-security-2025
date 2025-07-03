package com.geek.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *  1. In the new approach, developers are required to declare beans of type SecurityFilterChain and WebSecurityCustomizer to configure security settings.
 *      For example:
 *      - SecurityFilterChain bean is used to define HTTP security rules
 *      - WebSecurityCustomizer bean is used to configure web-level security settings
 *  2. In addition to SecurityFilterChain, Spring Security introduced th WebSecurityCustomizer interface, which can be used to customize WebSecurity.
 *     This i/f provides a way to ignore certain requests, which was previously done using the WebSecurityConfigurerAdapter.
 *  3. The transition from WebSecurityConfigurerAdapter to SecurityFilterChain involves changes such as not extending a class
 *     but instead declaring a bean (SecurityFilterChain) to configure security.
 *     Developers can use method references and lambda expressions to make the configuration more declarative and modular.
 *
 *  Conclusion:
 *      Overall, the deprecation of WebSecurityConfigurerAdapter marks a significant shift in how Spring Security is configured,
 *      promoting a more component-based and flexible approach.
 */

//@EnableWebSecurity(debug = true)
@Configuration
public class WebSecurityConfig {

    /**
     * Ensures that only authenticated users can see the secret greeting
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(requests -> {
            requests.requestMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated();
        }).formLogin(form -> {
            form.loginPage("/login").permitAll();
        }).logout(LogoutConfigurer::permitAll);

        return  httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // The builder will ensure the passwords are encoded before saving in memory
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
