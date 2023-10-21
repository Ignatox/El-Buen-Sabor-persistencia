package com.utn.EBS.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ConfigSeguridad {
    @Autowired
    private JwtFiltroAutenticacion jwtFiltroAutenticacion;
    @Autowired
    private AuthenticationProvider authProvider;

    // metodo para permitir requests a todas las rutas
    // sin autenticacion. Despues lo configuro bien.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                                .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/auth/**"))
                                .permitAll()
                                .and()
                                .authorizeHttpRequests()
                                .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/h2-console/**"))
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                .and()
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtFiltroAutenticacion, UsernamePasswordAuthenticationFilter.class)
                .headers()
                .frameOptions()
                .disable();
        return http.build();
    }
}
