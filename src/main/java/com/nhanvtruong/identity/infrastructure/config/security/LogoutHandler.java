package com.nhanvtruong.identity.infrastructure.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LogoutHandler implements ServerLogoutHandler {

  @Override
  public Mono<Void> logout(WebFilterExchange exchange, Authentication authentication) {
    return Mono.defer(() -> {
      ReactiveSecurityContextHolder.clearContext();
      return exchange.getExchange().getSession()
          .flatMap(WebSession::invalidate);
    });
  }
}
