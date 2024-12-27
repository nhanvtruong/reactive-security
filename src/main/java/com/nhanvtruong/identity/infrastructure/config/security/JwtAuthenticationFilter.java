package com.nhanvtruong.identity.infrastructure.config.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

  private final ReactiveUserDetailsService userDetailsService;

  @NonNull
  @Override
  public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
    String token = exchange.getRequest().getHeaders().getFirst("Authorization");
    if (token != null && token.startsWith("Bearer ")) {
      String username = "nhanvtruong";  // todo : extract token
      return userDetailsService.findByUsername(username)
          .flatMap(user -> {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities());
            return chain.filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
          });
    }
    return chain.filter(exchange);
  }
}
