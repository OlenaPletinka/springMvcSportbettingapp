package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Bet {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn
  private SportEvent sportEvent;

  @OneToMany
  private List<Outcome> outcomes;

  @Column
  private String description;

  @Column
  private String betType;
}
