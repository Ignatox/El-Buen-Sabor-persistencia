package com.utn.EBS.Config;  //Paquete de Filtros

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

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
                                //Aca irian los roles de cada uno de los usuarios de la pagina
                )
                .headers()
    }

}
