package com.nhanvtruong.identity.application.mapper;

import com.nhanvtruong.identity.domain.entities.TokenEntity;
import com.nhanvtruong.identity.infrastructure.persistence.model.TokenModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TokenDataMapper {

  TokenDataMapper INSTANCE = Mappers.getMapper(TokenDataMapper.class);

  TokenModel toTokenModel(TokenEntity tokenEntity);

  TokenEntity toTokenEntity(TokenModel tokenModel);

}
