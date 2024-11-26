package com.example.taskmanagementsystems.impl.security;

import com.example.taskmanagementsystems.db.repository.UserRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email)
        .map(userEntity -> new org.springframework.security.core.userdetails.User(
            userEntity.getEmail(),
            userEntity.getPassword(),
            Collections.singleton(userEntity.getRole())
        ))
        .orElseThrow(
            () -> new UsernameNotFoundException("User not found with email :" + email));
  }

}
