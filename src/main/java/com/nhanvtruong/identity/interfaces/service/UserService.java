package com.nhanvtruong.identity.interfaces.service;

import com.nhanvtruong.identity.interfaces.dto.res.UserCreatedResponseDto;
import com.nhanvtruong.identity.interfaces.dto.rq.UserCreatedRequestDto;
import reactor.core.publisher.Mono;

public interface UserService {

  Mono<UserCreatedResponseDto> createUser(UserCreatedRequestDto userCreatedRequestDto);

}
