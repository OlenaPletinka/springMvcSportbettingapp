package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class OutputBetObject {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String sportEventTitle;

  @OneToOne
  @JoinColumn
  private Outcome outcome;

  @Column
  private String outcomeValue;

  @Column
  private Double outcomeOddValue;

  @OneToOne
  @JoinColumn
  private OutcomeOdd outcomeOdd;

  @Column
  private Date fromDate;

  @Column
  private Date toDate;

  public OutputBetObject(String sportEventTitle, Outcome outcome, OutcomeOdd outcomeOdd,
                         Date fromDate, Date toDate) {
    this.sportEventTitle = sportEventTitle;
    this.outcome = outcome;
    this.outcomeValue = outcome.getOutcomeValue();
    this.outcomeOdd = outcomeOdd;
    this.outcomeOddValue = outcomeOdd.getValue();
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  @Override
  public String toString() {
    return " Bet on the " + sportEventTitle +
              " sport event, " + outcomeValue + ". The odd on this is " + outcomeOddValue +
              ", valid from " + fromDate + " to " + toDate;
  }
}
