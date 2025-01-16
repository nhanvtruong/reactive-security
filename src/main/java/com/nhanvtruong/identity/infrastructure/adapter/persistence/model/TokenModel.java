package com.nhanvtruong.identity.infrastructure.adapter.persistence.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tbl_tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TokenModel extends DateMetadata implements Persistable<UUID> {

  @Id
  private UUID sessionId;

  private String subject;

  private boolean revoked;

  @Transient
  private boolean newToken;

  @Override
  public UUID getId() {
    return sessionId;
  }

  @Override
  public boolean isNew() {
    return this.newToken || sessionId == null;
  }

  public TokenModel setAsNew(boolean newToken) {
    this.newToken = newToken;
    return this;
  }
}
