package com.nhanvtruong.identity.infrastructure.adapter.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClaimAttributes {

  // keys go here
  public static final String TOKEN_TYPE = "tokenType";

  public static final String SESSION_ID = "sessionId";

  // values go here
  public static final String ACCESS_TOKEN = "accessToken";

  public static final String REFRESH_TOKEN = "refreshToken";

}
