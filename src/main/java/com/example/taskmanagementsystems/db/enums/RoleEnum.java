package com.example.taskmanagementsystems.db.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
  ADMIN,
  USER;

  @Override
  public String getAuthority() {
    return name();
  }

}
