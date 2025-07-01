package com.example.demo.config;

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
import java.util.Map;


@Service
public class JwtService {

    private static final String SECRET_KEY = "4d75fa044ce265f1ba619cccd90e11241818ec0657a4c287461e26321b04a832";


    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {//метод для генерации токена
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // сколько будет доступен этот токен 24 часа выйдет
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUsername(String token) {//этот метод для извлечение username из токена
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {// метод для проверки валидности токена
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {//проерка на просрочность токена
        return extractAllClaims(token).getExpiration().before(new Date());
    }


    private Claims extractAllClaims(String token) {// этот метод достает все поля который пользователь положил
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private Key getSignInKey() {//подписывает токен
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}