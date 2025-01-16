package com.nhanvtruong.identity.infrastructure.adapter.persistence.repository;

import com.nhanvtruong.identity.infrastructure.adapter.persistence.model.TokenModel;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Mono;

public interface TokenReactiveRepository extends ReactiveBaseRepository<TokenModel, UUID> {

  @Query("UPDATE tbl_tokens SET revoked = true WHERE session_id = :sessionId")
  Mono<Void> invalidateSession(UUID sessionId);
}
