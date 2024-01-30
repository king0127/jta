package com.sdk.oauth.config;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomerCorsFilter extends CorsFilter {


    public CustomerCorsFilter(CorsConfigurationSource configSource) {
        super(configSource);
    }

    private static UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
            CorsConfiguration corsConfig = new CorsConfiguration();
            List<String> allowedHeaders = Arrays.asList("x-auth-teoken", "content-type", "X-Requested-With", "XMLHttpRequest","Access-Control-Allow-Origin","AAuthorization","authorization");
            List<String> exposedHeaders = Arrays.asList("x-auth-teoken", "content-type", "X-Requested-With", "XMLHttpRequest","Access-Control-Allow-Origin","Authorization","authorization");
            List<String> allowedMethods = Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS");
            List<String> allowedOrigins = Arrays.asList("*");
            corsConfig.setAllowedHeaders(allowedHeaders);
            corsConfig.setAllowedMethods(allowedMethods);
            corsConfig.setAllowedOrigins (allowedOrigins);
            corsConfig.setExposedHeaders(exposedHeaders);
            corsConfig.setMaxAge(36000L);
            corsConfig.setAllowCredentials(true);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfig);
            return source;
        }
}
