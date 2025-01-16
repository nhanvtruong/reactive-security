package com.nhanvtruong.identity.infrastructure.adapter.persistence.repository;

import static com.nhanvtruong.identity.infrastructure.adapter.security.ClaimAttributes.SESSION_ID;

import com.nhanvtruong.identity.application.mapper.TokenDataMapper;
import com.nhanvtruong.identity.application.port.TokenDataAdapter;
import com.nhanvtruong.identity.domain.entities.TokenEntity;
import com.nhanvtruong.identity.infrastructure.adapter.persistence.model.TokenModel;
import com.nhanvtruong.identity.infrastructure.adapter.security.TokenProvider;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TokenDataAdapterImpl implements TokenDataAdapter {

  private final TokenProvider tokenProvider;

  private final TokenReactiveRepository tokenReactiveRepository;

  @Override
  @Transactional
  public Mono<TokenEntity> generateToken(TokenEntity tokenEntity) {
    String accessToken = tokenProvider.generateAccessToken(tokenEntity);
    String refreshToken = tokenProvider.generateRefreshToken(tokenEntity);
    tokenEntity.setAccessToken(accessToken);
    tokenEntity.setRefreshToken(refreshToken);
    TokenModel tokenModel = TokenDataMapper.INSTANCE.toTokenModel(tokenEntity);
    tokenModel = tokenModel.setAsNew(true);
    return tokenReactiveRepository.save(tokenModel)
        .map(savedModel -> tokenEntity);
  }

  @Override
  public Mono<Void> invalidateToken(String authToken) {
    Map<String,Object> claims = tokenProvider.extractClaimsWithToken(authToken);
    UUID sessionId = UUID.fromString((String) claims.get(SESSION_ID));
    return tokenReactiveRepository.invalidateSession(sessionId);
  }


}
