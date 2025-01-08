package com.nhanvtruong.identity.infrastructure.config.security;

import com.nhanvtruong.identity.domain.entities.UserEntity;
import com.nhanvtruong.identity.infrastructure.config.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.function.Function;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final SecurityProperties securityProperties;

  @Override
  public JwtBuilder jwtTokenBuilder(UserEntity userDetails, Long expiresInMilliseconds) {
    return Jwts.builder()
        .subject(userDetails.getUsername())
        .issuedAt(Timestamp.from(Instant.now()))
        .expiration(Timestamp.from(Instant.now().plusMillis(expiresInMilliseconds)))
        .signWith(getSignInKey());
  }

  @Override
  public String generateAccessToken(UserEntity userDetails) {
    return jwtTokenBuilder(userDetails, securityProperties.getAccessTokenExpiration())
        .claim("tokenType", TokenType.ACCESS_TOKEN.name()).compact();
  }

  @Override
  public String generateRefreshToken(UserEntity userDetails) {
    return jwtTokenBuilder(userDetails, securityProperties.getRefreshTokenExpiration())
        .claim("tokenType", TokenType.REFRESH_TOKEN.name()).compact();
  }

  @Override
  public String extractSubjectFromToken(String token) {
    return extractValueFromClaims(token, Claims::getSubject);
  }

  @Override
  public Claims extractClaimsWithToken(String token) {
    return Jwts.parser()
        .verifyWith(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  @Override
  public <T> T extractValueFromClaims(String token, Function<Claims, T> claimsTFunction) {
    Claims claims = extractClaimsWithToken(token);
    return claimsTFunction.apply(claims);
  }

  public SecretKey getSignInKey() {
    byte[] keyByes = Decoders.BASE64.decode(securityProperties.getSecretKey());
    return Keys.hmacShaKeyFor(keyByes);
  }

}
