package com.nhanvtruong.identity.domain.entities;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity {

  private UUID sessionId;
  private String subject;
  private boolean expired;
  private String accessToken;
  private String refreshToken;
}
