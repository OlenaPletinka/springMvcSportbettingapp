package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class User {

  @Id
  @GeneratedValue
  Long id;

  @Column
  String password;

  @Column
  @Type(type = "org.hibernate.type.UUIDCharType")
  UUID accessToken;

  @Column(unique = true)
  String login;

  @Column
  LocalDateTime expirationTime;

  User(Long id, String password, String enabled, String login, UUID accessToken, LocalDateTime expirationTime) {
  }

  public User() {
  }
}
