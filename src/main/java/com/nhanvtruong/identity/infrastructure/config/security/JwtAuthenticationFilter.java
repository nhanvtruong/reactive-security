package com.nhanvtruong.identity.infrastructure.config.security;

import lombok.NonNull;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;

public interface JwtAuthenticationFilter extends WebFilter {

  boolean shouldNotFilter(@NonNull ServerWebExchange exchange);

}
