package com.nhanvtruong.identity.infrastructure.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeaderConstants extends HttpHeaders {

  public static final String BEARER = "Bearer ";

}
