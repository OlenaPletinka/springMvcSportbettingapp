package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.enums.Currency;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@Table
public class Wager {
  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  @JoinColumn
  private OutcomeOdd outcomeOdd;

  @OneToOne
  @JoinColumn
  private Player player;

  @Column
  private BigDecimal amount;

  @Enumerated
  private Currency currency;

  @Column
  private Integer timestamp;

  @Column
  private boolean isProcessed;

  @Column
  private boolean isWin;

  /**
   * constructor for the wager.
   *
   * @param outcomeOdd  given argument.
   * @param player      given argument.
   * @param amount      given argument.
   * @param currency    given argument.
   * @param timestamp   given argument.
   * @param isProcessed given argument.
   * @param isWin       given argument.
   */
  public Wager(Long id, OutcomeOdd outcomeOdd, Player player, BigDecimal amount, Currency currency,
               Integer timestamp, boolean isProcessed, boolean isWin) {
    this.outcomeOdd = outcomeOdd;
    this.player = player;
    this.amount = amount;
    this.currency = currency;
    this.timestamp = timestamp;
    this.isProcessed = isProcessed;
    this.isWin = isWin;
  }
}
