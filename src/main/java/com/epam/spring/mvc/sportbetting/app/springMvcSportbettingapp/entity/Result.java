package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Result {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany
  private List<Outcome> outcomes;
}
