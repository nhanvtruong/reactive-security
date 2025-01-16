package com.nhanvtruong.identity.infrastructure.adapter.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface ReactiveBaseRepository<T, K> extends R2dbcRepository<T, K> {

}
