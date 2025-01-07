package com.nhanvtruong.identity.interfaces.dto.rq;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserCreatedRequestDto(@NotNull String username, @NotNull String password,
                                    @Email String email) {
}
