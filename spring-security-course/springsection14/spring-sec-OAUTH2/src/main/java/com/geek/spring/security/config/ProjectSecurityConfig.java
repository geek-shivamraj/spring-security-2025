package com.geek.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/secure").authenticated().anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }

    /**
     * Instead of using the in-memory registration repository, we can also use the application.properties configurations
     *
     *  spring.security.oauth2.client.registration.github.client-id=${GITHUB_CLIENT_ID:Ov23liGyLBlm1TcK3z4W}
     *  spring.security.oauth2.client.registration.github.client-secret=${GITHUB_CLIENT_SECRET:d0dd3013ae0e4bdbc09fc48d5f1e518ede400ce2}
     *
     *  spring.security.oauth2.client.registration.facebook.client-id=${FACEBOOK_CLIENT_ID:974042741122392}
     *  spring.security.oauth2.client.registration.facebook.client-secret=${FACEBOOK_CLIENT_SECRET:36d48c25c1767d58b3101551513d7e1e}
     *
     */
    @Bean
    ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(githubClientRegistration(), facebookClientRegistration());
    }

    private ClientRegistration githubClientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("Ov23liGyLBlm1TcK3z4W")
                .clientSecret("d0dd3013ae0e4bdbc09fc48d5f1e518ede400ce2").build();
    }

    private ClientRegistration facebookClientRegistration() {
        return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook").clientId("974042741122392")
                .clientSecret("36d48c25c1767d58b3101551513d7e1e").build();
    }

}
