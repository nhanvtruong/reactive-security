package com.nhanvtruong.identity.infrastructure.config.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class PropertiesConfig {

  @Bean
  public SecurityProperties securityProperties() {
    return new SecurityProperties();
  }

}
