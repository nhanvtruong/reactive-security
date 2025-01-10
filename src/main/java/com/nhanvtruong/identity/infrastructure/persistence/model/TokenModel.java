package com.nhanvtruong.identity.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tbl_tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenModel extends Metadata {

  @Id
  private String sessionId;
  private String subject;
  private boolean expired;

}
