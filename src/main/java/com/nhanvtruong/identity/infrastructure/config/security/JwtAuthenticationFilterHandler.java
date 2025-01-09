package com.nhanvtruong.identity.infrastructure.config.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.nhanvtruong.identity.application.constants.HeaderConstants;
import com.nhanvtruong.identity.application.exceptions.InvalidToken;
import java.util.Collection;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilterHandler implements JwtAuthenticationFilter {

  private final ReactiveUserDetailsService userDetailsService;

  private final TokenServiceImpl tokenServiceImpl;

  @NonNull
  @Override
  public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {

    if (shouldNotFilter(exchange)) {
      return authenticatePublicEndpointAccess(exchange, chain);
    }

    // extract username from Authorization Header
    String username = Optional.ofNullable(
            exchange.getRequest().getHeaders().getFirst(AUTHORIZATION))
        .filter(token -> token.startsWith(HeaderConstants.BEARER))
        .map(token -> token.substring(HeaderConstants.BEARER.length()))
        .map(tokenServiceImpl::extractSubjectFromToken)
        .orElseThrow(() -> new InvalidToken("Invalid JWT Token"));

    return userDetailsService.findByUsername(username)
        .switchIfEmpty(Mono.error(new UsernameNotFoundException("Username not found")))
        .flatMap(
            userDetails ->
                authenticateUser(userDetails.getUsername(), userDetails.getAuthorities(), chain,
                    exchange));
  }

  private Mono<Void> authenticateUser(String username,
      Collection<? extends GrantedAuthority> grantedAuthorities, WebFilterChain chain,
      ServerWebExchange exchange) {
    Authentication authentication = new UsernamePasswordAuthenticationToken(
        username,
        null, // No need for the credential since using toke-base validation
        grantedAuthorities
    );
    return chain.filter(exchange)
        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
  }

  private Mono<Void> authenticatePublicEndpointAccess(
      ServerWebExchange exchange, WebFilterChain chain) {
    return authenticateUser("anonymous", null, chain, exchange);
  }

  @Override
  public boolean shouldNotFilter(@NonNull ServerWebExchange exchange) {
    String path = exchange.getRequest().getURI().getPath();
    return path.startsWith("/public");
  }
}
