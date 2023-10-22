package com.utn.EBS.Utils;

import com.utn.EBS.Entidades.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final Key SECRET_KEY = generateSecretKey();

    private static Key generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32]; // 256 bits
        secureRandom.nextBytes(keyBytes);
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }
    public String getToken(Usuario usuario) throws Exception {
        try {
            return getToken(new HashMap<>(), usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    private String getToken(Map<String, Object> extraClaims, Usuario usuario) throws Exception {
        try {
            return Jwts
                    .builder()
                    .setClaims(extraClaims)
                    // en vez de username devolvemos el email en este metodo
                    .setSubject(usuario.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
