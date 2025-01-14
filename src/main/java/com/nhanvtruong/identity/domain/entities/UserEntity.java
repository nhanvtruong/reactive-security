package com.nhanvtruong.identity.domain.entities;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  private String username;
  private String password;
  private String email;
  private UUID sessionId;

  public UUID generateSessionId() {
    sessionId = UUID.randomUUID();
    return sessionId;
  }
}
