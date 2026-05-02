package com.algotracker.AlgotrackerProject.security.jwt;

import com.algotracker.AlgotrackerProject.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSignInKey() {
        byte[] keyBytes =
                //Decoders.BASE64.decode(secretKey);
                secretKey.getBytes(StandardCharsets.UTF_8);

        //Hash-based Message Authentication Code.
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(
                                System.currentTimeMillis() + 1000 * 60 * 60
                        )
                )
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUserName(String authToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(authToken)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String jwtToken, User user) {
        String extractedEmail = extractUserName(jwtToken);

        return extractedEmail.equals(user.getEmail()) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getExpiration();

        return expirationDate.before(new Date(System.currentTimeMillis()));
    }
}
