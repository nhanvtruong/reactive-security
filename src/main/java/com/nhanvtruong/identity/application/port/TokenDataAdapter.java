package com.nhanvtruong.identity.application.port;

import com.nhanvtruong.identity.domain.entities.TokenEntity;
import reactor.core.publisher.Mono;

public interface TokenDataAdapter {

  Mono<TokenEntity> generateToken(TokenEntity tokenEntity);

  Mono<Void> invalidateToken(String authToken);
}
