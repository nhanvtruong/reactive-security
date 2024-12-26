package com.nhanvtruong.identity.application.cqrs.command;

import com.nhanvtruong.identity.application.mapper.UserDataMapper;
import com.nhanvtruong.identity.application.port.UserDataAdapter;
import com.nhanvtruong.identity.domain.entities.UserEntity;
import com.nhanvtruong.identity.interfaces.dto.res.UserCreatedResponseDto;
import com.nhanvtruong.identity.interfaces.dto.rq.UserCreatedRequestDto;
import com.nhanvtruong.identity.interfaces.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserCommandHandler implements UserService {

  private final UserDataAdapter userDataAdapter;

  @Override
  public Mono<UserCreatedResponseDto> createUser(UserCreatedRequestDto userCreatedRequestDto) {
    UserEntity userEntity = UserDataMapper.INSTANCE.toUserEntity(userCreatedRequestDto);
    return userDataAdapter.createUser(userEntity);
  }
}
