package com.nhanvtruong.identity.infrastructure.adapter.persistence.repository;

import com.nhanvtruong.identity.infrastructure.adapter.persistence.model.TokenModel;
import java.util.UUID;

public interface TokenReactiveRepository extends ReactiveBaseRepository<TokenModel, UUID> {

}
