package com.nhanvtruong.identity.infrastructure.persistence.repository;

import com.nhanvtruong.identity.infrastructure.persistence.model.UserModel;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Mono;

public interface UserReactiveRepository extends ReactiveBaseRepository<UserModel, Long> {

  @Query(value = "INSERT INTO UserModel values ")
  <T> Mono<T> saveUser(UserModel user);

}
