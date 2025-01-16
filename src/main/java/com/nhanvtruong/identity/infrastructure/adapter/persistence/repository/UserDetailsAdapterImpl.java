package com.nhanvtruong.identity.infrastructure.adapter.persistence.repository;

import com.nhanvtruong.identity.application.port.UserDetailsAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserDetailsAdapterImpl implements UserDetailsAdapter {

  private final UserReactiveRepository userReactiveRepository;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return userReactiveRepository.findByUsername(username, UserDetails.class);
  }
}
