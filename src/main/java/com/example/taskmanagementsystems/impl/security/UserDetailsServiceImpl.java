package com.example.taskmanagementsystems.impl.security;

import com.example.taskmanagementsystems.db.entity.UserEntity;
import com.example.taskmanagementsystems.db.repository.UserRepository;
import com.example.taskmanagementsystems.impl.exception.AppException;
import com.example.taskmanagementsystems.impl.exception.EnumException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByEmail(email)
        .orElseThrow(() ->
            new AppException(EnumException.NOT_FOUND,
                String.format("User with email %s not found!", email)));
    return new UserDetailsImpl(userEntity);

  }

}
