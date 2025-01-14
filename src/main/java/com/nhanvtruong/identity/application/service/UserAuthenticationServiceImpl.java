package com.nhanvtruong.identity.application.service;

import static com.nhanvtruong.identity.application.exceptions.enums.ErrorsEnum.INCORRECT_USER_NAME_PASSWORD;

import com.nhanvtruong.identity.application.exceptions.IncorrectUsernamePasswordException;
import com.nhanvtruong.identity.application.mapper.UserDataMapper;
import com.nhanvtruong.identity.application.port.TokenDataAdapter;
import com.nhanvtruong.identity.application.port.UserDetailsAdapter;
import com.nhanvtruong.identity.domain.aggregates.UserLoginAggregate;
import com.nhanvtruong.identity.domain.entities.TokenEntity;
import com.nhanvtruong.identity.interfaces.dto.res.UserLoginRequestDto;
import com.nhanvtruong.identity.interfaces.dto.rq.UserLoginResponseDto;
import com.nhanvtruong.identity.interfaces.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

  private final TokenDataAdapter tokenDataAdapter;

  private final UserDetailsAdapter userDetailsAdapter;

  private final PasswordEncoder passwordEncoder;

  @Override
  public Mono<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto) {
    return userDetailsAdapter.findByUsername(userLoginRequestDto.username())
        .filter(userDetails ->
            passwordEncoder.matches(userLoginRequestDto.password(), userDetails.getPassword()))
        .switchIfEmpty(
            Mono.error(new IncorrectUsernamePasswordException(INCORRECT_USER_NAME_PASSWORD)))
        .map(UserDataMapper.INSTANCE::toUserEntity)
        .flatMap(userEntity -> {
          UserLoginAggregate loginAggregate = UserLoginAggregate.builder().user(userEntity).build();
          TokenEntity tokenDetails = loginAggregate.buildTokenEntity();
          return tokenDataAdapter.generateToken(tokenDetails)
              .map(token ->
                  new UserLoginResponseDto(token.getAccessToken(), token.getRefreshToken()));
        });
  }

  @Override
  public Mono<Void> logout(String authToken) {
    return null;
  }

}
