package com.nhanvtruong.identity.infrastructure.persistence.repository;

import com.nhanvtruong.identity.application.mapper.TokenDataMapper;
import com.nhanvtruong.identity.application.port.TokenDataAdapter;
import com.nhanvtruong.identity.domain.entities.TokenEntity;
import com.nhanvtruong.identity.infrastructure.config.security.TokenService;
import com.nhanvtruong.identity.infrastructure.persistence.model.TokenModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TokenDataAdapterImpl implements TokenDataAdapter {

  private final TokenService tokenService;

  private final TokenReactiveRepository tokenReactiveRepository;

  @Override
  @Transactional
  public Mono<TokenEntity> generateToken(TokenEntity tokenEntity) {
    String accessToken = tokenService.generateAccessToken(tokenEntity);
    String refreshToken = tokenService.generateRefreshToken(tokenEntity);
    tokenEntity.setAccessToken(accessToken);
    tokenEntity.setRefreshToken(refreshToken);
    TokenModel tokenModel = TokenDataMapper.INSTANCE.toTokenModel(tokenEntity);
    tokenModel = tokenModel.setAsNew(true);
    return tokenReactiveRepository.save(tokenModel)
        .map(savedModel -> tokenEntity);
  }


}
