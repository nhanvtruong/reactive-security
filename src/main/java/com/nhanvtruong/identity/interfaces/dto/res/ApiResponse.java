package com.nhanvtruong.identity.interfaces.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
  private T data;
}
