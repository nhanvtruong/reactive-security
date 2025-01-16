package com.nhanvtruong.identity.infrastructure.persistence.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
public class DateMetadata {

  @LastModifiedBy
  protected String lastModifiedBy;
  @LastModifiedDate
  protected LocalDateTime lastModifiedDate;

}
