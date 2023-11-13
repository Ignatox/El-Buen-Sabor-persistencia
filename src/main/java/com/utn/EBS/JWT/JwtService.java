package com.utn.EBS.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;



@Service
public class JwtService {
    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    //Escribir la SECRET_KEY que les pinte si lo desean

    public String getToken (UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(HashMap<String, Object> extraClaims, UserDetails user) {   //Generador de Token
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())  //Es un getUsername del UserDetails
                .setIssuedAt(new Date(System.currentTimeMillis()))    //Metodo parecido a fecha_alta
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))   //Metodo de expiracion, sumado con fecha harcodeada equivalente a un dia
                .signWith(getKey(), SignatureAlgorithm.HS256)   //Hay varios algoritmos para usar
                .compact();
    }

    private Key getKey () {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }
}
