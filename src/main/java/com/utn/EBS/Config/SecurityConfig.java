package com.utn.EBS.Config;



import com.utn.EBS.Enumeraciones.Rol;
import com.utn.EBS.Enumeraciones.RolUsuario;
import com.utn.EBS.JWT.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration //Indica que esta clase es de configuracion, configura los objetos que necesita para el login y registerCliente
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                                authRequest
                                        //Autenticacion
                                        .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
                                        .requestMatchers(new AntPathRequestMatcher("/api/v1/**")).permitAll()
                                        .requestMatchers(new AntPathRequestMatcher("/api/v1/**")).hasAuthority(RolUsuario.ADMINISTRADOR.toString())
                                        .requestMatchers(new AntPathRequestMatcher("/api/v1/usuarios/crearUsuario")).permitAll()
                                        .requestMatchers(new AntPathRequestMatcher("/api/v1/empleado/registrarEmpleado")).hasAuthority(RolUsuario.ADMINISTRADOR.toString())
                                        .requestMatchers(new AntPathRequestMatcher("/api/v1/pedidos/crearPedido")).hasAuthority(RolUsuario.CLIENTE.toString())
                                        //.requestMatchers(new AntPathRequestMatcher("/api/v1/ingredientes", "POST")).hasAuthority(RolUsuario.CLIENTE.toString())
                                        //Consola H2
                                        .requestMatchers(new AntPathRequestMatcher("/api/v1/clientes/modificarCliente")).hasAuthority(RolUsuario.ADMINISTRADOR.toString())
                                        .requestMatchers(new AntPathRequestMatcher("/api/v1/clientes/mostrarclientes")).hasAuthority(RolUsuario.ADMINISTRADOR.toString())

                                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                                        .anyRequest().permitAll()
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) //H2
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();



    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(List.of("Authorization", "Access-Control-Allow-Origin", "Content-Type",
                "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
