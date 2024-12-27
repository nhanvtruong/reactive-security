package com.nhanvtruong.identity.infrastructure.persistence.repository;

import com.nhanvtruong.identity.infrastructure.persistence.model.UserModel;
import reactor.core.publisher.Mono;

public interface UserReactiveRepository extends ReactiveBaseRepository<UserModel, Long> {

  <T> Mono<T> findByUsername(String username, Class<T> clazz);

}
