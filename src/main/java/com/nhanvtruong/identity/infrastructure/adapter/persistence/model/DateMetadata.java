package com.nhanvtruong.identity.infrastructure.adapter.persistence.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
public class DateMetadata {

  @CreatedDate
  protected LocalDateTime createdDate;
  @LastModifiedDate
  protected LocalDateTime lastModifiedDate;

}
