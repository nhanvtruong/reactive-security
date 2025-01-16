package com.nhanvtruong.identity.infrastructure.adapter.security;

import static com.nhanvtruong.identity.infrastructure.adapter.security.ClaimAttributes.ACCESS_TOKEN;
import static com.nhanvtruong.identity.infrastructure.adapter.security.ClaimAttributes.REFRESH_TOKEN;
import static com.nhanvtruong.identity.infrastructure.adapter.security.ClaimAttributes.SESSION_ID;
import static com.nhanvtruong.identity.infrastructure.adapter.security.ClaimAttributes.TOKEN_TYPE;

import com.nhanvtruong.identity.domain.entities.TokenEntity;
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
public class TokenProviderImpl implements TokenProvider {

  private final SecurityProperties securityProperties;

  @Override
  public JwtBuilder jwtTokenBuilder(TokenEntity tokenEntity, Long expiresInMilliseconds) {
    return Jwts.builder()
        .subject(tokenEntity.getSubject())
        .issuedAt(Timestamp.from(Instant.now()))
        .expiration(Timestamp.from(Instant.now().plusMillis(expiresInMilliseconds)))
        .signWith(getSignInKey());
  }

  @Override
  public String generateAccessToken(TokenEntity tokenDetails) {
    return jwtTokenBuilder(tokenDetails, securityProperties.getAccessTokenExpiration())
        .claim(TOKEN_TYPE, ACCESS_TOKEN)
        .claim(SESSION_ID, tokenDetails.getSessionId())
        .compact();
  }

  @Override
  public String generateRefreshToken(TokenEntity tokenDetails) {
    return jwtTokenBuilder(tokenDetails, securityProperties.getRefreshTokenExpiration())
        .claim(TOKEN_TYPE, REFRESH_TOKEN)
        .claim(SESSION_ID, tokenDetails.getSessionId())
        .compact();
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

  private SecretKey getSignInKey() {
    byte[] keyByes = Decoders.BASE64.decode(securityProperties.getSecretKey());
    return Keys.hmacShaKeyFor(keyByes);
  }

}
