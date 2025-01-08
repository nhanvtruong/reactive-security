package com.nhanvtruong.identity.application.mapper;

import com.nhanvtruong.identity.domain.entities.UserEntity;
import com.nhanvtruong.identity.infrastructure.persistence.model.UserModel;
import com.nhanvtruong.identity.interfaces.dto.res.UserCreatedResponseDto;
import com.nhanvtruong.identity.interfaces.dto.rq.UserCreatedRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserDataMapper {

  UserDataMapper INSTANCE = Mappers.getMapper(UserDataMapper.class);

  UserEntity toUserEntity(UserCreatedRequestDto requestDto);

  @Mapping(source = "password", target = "passwordHashed")
  UserModel toUserModel(UserEntity entity);

  UserCreatedResponseDto toResponseDto(UserModel userModel);

  UserEntity toUserEntity(UserDetails userDetails);
}
