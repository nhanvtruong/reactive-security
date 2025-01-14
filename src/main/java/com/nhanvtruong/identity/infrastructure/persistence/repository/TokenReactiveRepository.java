package com.nhanvtruong.identity.infrastructure.persistence.repository;

import com.nhanvtruong.identity.infrastructure.persistence.model.TokenModel;
import java.util.UUID;

public interface TokenReactiveRepository extends ReactiveBaseRepository<TokenModel, UUID> {

}
