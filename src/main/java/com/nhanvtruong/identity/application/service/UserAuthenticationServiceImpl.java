package com.nhanvtruong.identity.application.service;

import com.nhanvtruong.identity.application.mapper.UserDataMapper;
import com.nhanvtruong.identity.application.port.UserDataAdapter;
import com.nhanvtruong.identity.application.port.UserDetailsAdapter;
import com.nhanvtruong.identity.infrastructure.config.security.TokenService;
import com.nhanvtruong.identity.interfaces.dto.res.UserLoginRequestDto;
import com.nhanvtruong.identity.interfaces.dto.rq.UserLoginResponseDto;
import com.nhanvtruong.identity.interfaces.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

  private final UserDataAdapter userDataAdapter;

  private final TokenService tokenService;

  private final UserDetailsAdapter userDetailsAdapter;

  private final PasswordEncoder passwordEncoder;

  @Override
  public Mono<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto) {
    return userDetailsAdapter.findByUsername(userLoginRequestDto.username())
        .filter(userDetails ->
            passwordEncoder.matches(userLoginRequestDto.password(), userDetails.getPassword()))
        .switchIfEmpty(Mono.error(new BadCredentialsException("Username or password is incorrect")))
        .map(UserDataMapper.INSTANCE::toUserEntity)
        .flatMap(userEntity -> {
          String accessToken = tokenService.generateAccessToken(userEntity);
          String refreshToken = tokenService.generateRefreshToken(userEntity);
          return Mono.just(new UserLoginResponseDto(accessToken, refreshToken));
        });
  }

  @Override
  public Mono<Void> logout(String authToken) {
    return null;
  }

}
