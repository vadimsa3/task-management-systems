package com.example.taskmanagementsystems.db.entity;

import com.example.taskmanagementsystems.db.enums.RoleEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class UserEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "user_id")
  private UUID userId;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String email;

//  @OneToMany(mappedBy = "author",
//      cascade = CascadeType.ALL,
//      orphanRemoval = true,
//      fetch = FetchType.LAZY)
//  @ToString.Exclude
//  private List<TaskEntity> authoredTasks;
//
//  @OneToMany(mappedBy = "executor",
//      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
//      fetch = FetchType.LAZY)
//  @ToString.Exclude
//  private List<TaskEntity> executedTask;

  private String password;

  @Enumerated(EnumType.STRING)
  private RoleEnum role;

  private LocalDateTime userRegistrationDate;

}
