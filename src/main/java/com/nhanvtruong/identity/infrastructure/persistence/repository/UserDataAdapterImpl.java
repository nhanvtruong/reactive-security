package com.nhanvtruong.identity.infrastructure.persistence.repository;

import com.nhanvtruong.identity.application.mapper.UserDataMapper;
import com.nhanvtruong.identity.application.port.UserDataAdapter;
import com.nhanvtruong.identity.domain.entities.UserEntity;
import com.nhanvtruong.identity.infrastructure.exceptions.UserCreateFailedException;
import com.nhanvtruong.identity.infrastructure.persistence.model.UserModel;
import com.nhanvtruong.identity.interfaces.dto.res.UserCreatedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserDataAdapterImpl implements UserDataAdapter {

  private final PasswordEncoder passwordEncoder;

  private final UserReactiveRepository userReactiveRepository;

  @Override
  @Transactional
  public Mono<UserCreatedResponseDto> createUser(UserEntity user) {
    user.setPasswordHashed(passwordEncoder.encode(user.getPasswordHashed()));
    UserModel userModel = UserDataMapper.INSTANCE.toUserModel(user);
    return userReactiveRepository.save(userModel)
        .map(UserDataMapper.INSTANCE::toResponseDto)
        .onErrorResume(e -> Mono.error(new UserCreateFailedException("Failed to create user")));
  }
}
