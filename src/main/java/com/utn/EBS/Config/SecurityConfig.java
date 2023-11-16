package com.utn.EBS.Config;  //Paquete de Filtros

import com.utn.EBS.JWT.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
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

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csfr -> csfr.disable()) //Se deshabilita para que no pida el token generico
                .authorizeHttpRequests(authRequest ->
                        authRequest
                            //Autenticacion
                                .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
                                //Matchea con todos los registros publicos, todos los que quieran ingresar a la pag
                                //En teoria no se usa esta ruta, sino las otras para el login

                            //Consola H2:
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                //Permite que ingrese cualquiera a la base de datos, por las dudas ver esto

                                .requestMatchers("/api/v1/demoAdmin/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/demoUser/**").hasAuthority("USER")
                                //Aca irian los roles de cada uno de los usuarios de la pagina, CAMBIAR
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
    CorsConfigurationSource corsConfigurationSource() {                                 //El BEAN de CORS que dijo el profesor que agregaramos en la leccion
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
