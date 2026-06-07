//package com.example.Event_Management_System.Config;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    private final Key key;
//    private final long jwtExpirationInMs;
//
//    public JwtTokenProvider(@Value("${app.jwt.secret}") String secret,
//                            @Value("${app.jwt.expiration-in-ms}") long jwtExpirationInMs) {
//        this.key = Keys.hmacShaKeyFor(secret.getBytes());
//        this.jwtExpirationInMs = jwtExpirationInMs;
//    }
//
//    public String generateToken(org.springframework.security.core.Authentication authentication) {
//        var principal = (com.example.Event_Management_System.Config.UserPrincipal) authentication.getPrincipal();
//        Date now = new Date();
//        Date expiry = new Date(now.getTime() + jwtExpirationInMs);
//
//        return Jwts.builder()
//                .setSubject(Long.toString(principal.getId()))
//                .setIssuedAt(now)
//                .setExpiration(expiry)
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public Long getUserIdFromJWT(String token) {
//        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//        return Long.parseLong(claims.getSubject());
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
//            return true;
//        } catch (JwtException | IllegalArgumentException ex) {
//            // invalid token
//            return false;
//        }
//    }
//}
