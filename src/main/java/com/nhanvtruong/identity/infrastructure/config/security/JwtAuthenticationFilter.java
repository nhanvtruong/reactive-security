package com.nhanvtruong.identity.infrastructure.config.security;

import com.nhanvtruong.identity.infrastructure.constants.HeaderConstants;
import com.nhanvtruong.identity.infrastructure.exceptions.InvalidToken;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

  private final ReactiveUserDetailsService userDetailsService;

  private final TokenService tokenService;

  @NonNull
  @Override
  public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {

    // extract username from Authorization Header
    String username = Optional.ofNullable(
            exchange.getRequest().getHeaders().getFirst(HeaderConstants.AUTHORIZATION))
        .filter(token -> token.startsWith(HeaderConstants.BEARER))
        .map(token -> token.substring(HeaderConstants.BEARER.length()))
        .map(tokenService::extractToken)
        .orElseThrow(() -> new InvalidToken("Invalid JWT Token"));

    return userDetailsService.findByUsername(username)
        .switchIfEmpty(Mono.error(new UsernameNotFoundException("Username not found")))
        .flatMap(userDetails -> {
          Authentication authentication = new UsernamePasswordAuthenticationToken(
              userDetails.getUsername(),
              null, // No need for the credential since using toke-base validation
              userDetails.getAuthorities()
          );
          return chain.filter(exchange)
              .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
        });
  }
}
