package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class SportEvent {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String title;

  @OneToOne
  @JoinColumn
  private Result result;

  @OneToMany
  private List<Bet> bets;

  @Column
  private LocalDate startDate;

  @Column
  private LocalDate endDate;
}
