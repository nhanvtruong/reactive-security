package com.nhanvtruong.identity.infrastructure.persistence.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
public class Metadata {

  @CreatedBy
  protected String createdBy;
  @CreatedDate
  protected Date createdDate;
  @LastModifiedBy
  protected String lastModifiedBy;
  @LastModifiedDate
  protected Date lastModifiedDate;

}
