package com.nhanvtruong.identity.domain.aggregates;

import com.nhanvtruong.identity.domain.entities.TokenEntity;
import com.nhanvtruong.identity.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginAggregate {

  private UserEntity user;
  private TokenEntity token;

  public TokenEntity buildTokenEntity() {
    token = TokenEntity.builder()
        .subject(user.getUsername())
        .sessionId(user.generateSessionId())
        .revoked(false)
        .build();
    return token;
  }


}
