package com.nhanvtruong.identity.infrastructure.config.security;

import com.nhanvtruong.identity.infrastructure.config.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {

  private final SecurityProperties securityProperties;

  public String extractToken(String token) {
    return token;
  }

//  public String generateToken(UserDetails userDetails, Long expiresInMiliseconds) {
//
//  }

}
