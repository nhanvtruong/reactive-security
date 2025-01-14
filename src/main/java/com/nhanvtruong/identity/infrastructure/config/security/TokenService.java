package com.nhanvtruong.identity.infrastructure.config.security;

import com.nhanvtruong.identity.domain.entities.TokenEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import java.util.function.Function;

public interface TokenService {

  JwtBuilder jwtTokenBuilder(TokenEntity tokenDetails, Long expiresInMilliseconds);

  String generateAccessToken(TokenEntity tokenDetails );

  String generateRefreshToken(TokenEntity tokenDetails);

  String extractSubjectFromToken(String token);

  Claims extractClaimsWithToken(String token);

  <T> T extractValueFromClaims(String token, Function<Claims, T> claimsTFunction);

}
