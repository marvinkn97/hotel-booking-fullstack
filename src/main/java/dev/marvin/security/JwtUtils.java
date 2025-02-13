package dev.marvin.security;

import dev.marvin.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${jwt.secretKeyString}")
    String secretKeyString;

    private SecretKey secretKey;

    @Value("${jwt.issuer}")
    String issuer;

    @Value("${jwt.accessTokenExpirationInMs}")
    Long accessTokenExpirationInMs;

    @Value("${jwt.refreshTokenExpirationInMs}")
    Long refreshTokenExpirationInMs;

    @PostConstruct
    private void init() {
        byte[] keyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        this.secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public Map<String, Object> generateAccessAndRefreshToken(User user) {
        List<String> roles = user.getAuthorities().stream()
                .map(role -> "ROLE_" + role.getAuthority())
                .toList();

        String accessToken = Jwts.builder()
                .subject(user.getUsername())
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpirationInMs))
                .claims(Map.of("role", roles))
                .signWith(secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpirationInMs))
                .signWith(secretKey)
                .compact();

        return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    }

    public String getUsernameFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

}
