package com.nhanvtruong.identity.application.port;

import com.nhanvtruong.identity.domain.entities.UserEntity;
import com.nhanvtruong.identity.interfaces.dto.res.UserCreatedResponseDto;
import reactor.core.publisher.Mono;

public interface UserDataAdapter {

  Mono<UserCreatedResponseDto> createUser(UserEntity user);

}
