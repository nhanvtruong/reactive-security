package com.nhanvtruong.identity.infrastructure.config.security;

import com.nhanvtruong.identity.domain.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import java.util.function.Function;

public interface TokenService {

  JwtBuilder jwtTokenBuilder(UserEntity userDetails, Long expiresInMilliseconds);

  String generateAccessToken(UserEntity userDetails);

  String generateRefreshToken(UserEntity userDetails);

  String extractSubjectFromToken(String token);

  Claims extractClaimsWithToken(String token);

  <T> T extractValueFromClaims(String token, Function<Claims, T> claimsTFunction);

}
