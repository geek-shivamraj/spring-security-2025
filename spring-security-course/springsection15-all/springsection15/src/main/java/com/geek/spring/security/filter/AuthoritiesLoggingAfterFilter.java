package com.geek.spring.security.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

/**
 * Filter to log user authentication is successful with so & so authorities
 * This filter will be executed after BasicAuthenticationFilter
 */

@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(null != authentication) {
            log.info("Authentication successful for user: {}, with authorities: {}", authentication.getName(),
                    authentication.getAuthorities());
        }
        chain.doFilter(request, response);
    }
}
