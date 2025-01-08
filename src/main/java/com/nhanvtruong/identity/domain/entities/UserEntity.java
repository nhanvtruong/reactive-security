package com.nhanvtruong.identity.domain.entities;

import com.nhanvtruong.identity.application.exceptions.IncorrectPasswordException;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  private String username;
  private String password;
  private String email;

  public void checkPassword(String password) {
    if (!Objects.equals(password, this.password)) {
      throw new IncorrectPasswordException("Wrong password");
    }
  }
}
