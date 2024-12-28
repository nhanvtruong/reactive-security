package com.nhanvtruong.identity.infrastructure.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.security")
public class SecurityProperties {

  private Long accessTokenExpiration;
  private Long refreshTokenExpiration;
  private String secretKey;
}
