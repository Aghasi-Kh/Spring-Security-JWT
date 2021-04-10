package com.example.springsecurity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User extends BaseEntity {

  private String username;

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_roles",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
  private List<Role> roles;
}
