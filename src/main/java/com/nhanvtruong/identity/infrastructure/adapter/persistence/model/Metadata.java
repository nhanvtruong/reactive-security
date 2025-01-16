package com.nhanvtruong.identity.infrastructure.adapter.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Getter
@Setter
public class Metadata extends DateMetadata {

  @CreatedBy
  protected String createdBy;
  @LastModifiedBy
  protected String lastModifiedBy;

}
