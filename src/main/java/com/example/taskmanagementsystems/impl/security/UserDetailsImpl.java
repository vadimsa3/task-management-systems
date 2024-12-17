package com.example.taskmanagementsystems.impl.security;

import com.example.taskmanagementsystems.db.entity.UserEntity;
import com.example.taskmanagementsystems.db.enums.RoleEnum;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

  private transient UserEntity userEntity;

  public UUID getId() {
    return userEntity.getUserId();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority( "ROLE_" + userEntity.getRole().name() ) );
  }

  @Override
  public String getPassword() {
    return userEntity.getPassword();
  }

  @Override
  public String getUsername() {
    return userEntity.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
