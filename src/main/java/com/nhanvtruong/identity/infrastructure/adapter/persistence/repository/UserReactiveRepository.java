package com.nhanvtruong.identity.infrastructure.adapter.persistence.repository;

import com.nhanvtruong.identity.infrastructure.adapter.persistence.model.UserModel;
import reactor.core.publisher.Mono;

interface UserReactiveRepository extends ReactiveBaseRepository<UserModel, Long> {

  <T> Mono<T> findByUsername(String username, Class<T> clazz);

}
