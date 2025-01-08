package com.nhanvtruong.identity.interfaces.dto.res;

import jakarta.validation.constraints.NotNull;

public record UserLoginRequestDto(@NotNull String username,@NotNull String password) {

}
