package com.nhanvtruong.identity.infrastructure.persistence.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
public class Metadata extends Datemetadata{

  @CreatedBy
  protected String createdBy;
  @CreatedDate
  protected LocalDateTime createdDate;

}
