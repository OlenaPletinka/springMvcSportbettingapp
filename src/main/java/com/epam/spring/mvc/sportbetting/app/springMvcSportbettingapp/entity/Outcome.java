package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Outcome {
  @Id
  @GeneratedValue
  private Long id;

  @OneToMany
  private List<OutcomeOdd> outcomeOdds;

  @Column
  private String outcomeValue;

  @ManyToOne
  @JoinColumn
  private Bet bet;

  @ManyToOne
  @JoinColumn
  private Result result;
}
