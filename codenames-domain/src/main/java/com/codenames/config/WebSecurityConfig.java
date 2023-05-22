package com.codenames.config;


import com.codenames.exception.UserAuthEntryPoint;
import com.codenames.filter.JwtAuthFilter;
import com.codenames.properties.CodeNamesProperties;
import com.codenames.provider.UserAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CodeNamesProperties codeNamesProperties;

    private final UserAuthProvider userAuthProvider;

    private final UserAuthEntryPoint userAuthEntryPoint;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .cors().and()
                .exceptionHandling().authenticationEntryPoint(userAuthEntryPoint)
                .and()
                .addFilterBefore(new JwtAuthFilter(userAuthProvider), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST , "/login", "/register").permitAll()
<<<<<<< HEAD
                        .requestMatchers("/whoami").permitAll()
=======
>>>>>>> c272801cf53c51aafc9e629308295f7a0a6d9bd7
                        .requestMatchers("/socket/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(codeNamesProperties.getRestController().getCrossOrigin()));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
