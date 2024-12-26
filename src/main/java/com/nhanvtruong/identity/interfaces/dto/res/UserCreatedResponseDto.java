package com.nhanvtruong.identity.interfaces.dto.res;

import lombok.Builder;

@Builder
public record UserCreatedResponseDto(String username, String email) {

}
