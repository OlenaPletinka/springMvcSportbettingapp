package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class OutcomeOdd {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn
  private Outcome outcome;

  @Column
  private Double value;

  @Column
  private Date fromDate;

  @Column
  private Date toDate;

  @Override
  public String toString() {
    return String.format("The winner is Outcome %d  [value=%f and valid from %s to %s]"
              , outcome.getId(), value, fromDate, toDate);
  }
}
