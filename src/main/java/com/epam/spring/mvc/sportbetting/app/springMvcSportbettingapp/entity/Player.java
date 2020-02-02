package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.enums.Currency;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Player extends User {

  @Column
  private String name;

  @Column
  private String accountNumber;

  @Column
  private BigDecimal balance;

  @Enumerated
  private Currency currency;

  @Column
  private LocalDate birth;

  @Builder
  public Player(Long id, String password, String enabled, @UniqueElements String login, UUID accessToken,
                LocalDateTime expirationTime, String name, String accountNumber, BigDecimal balance,
                Currency currency, LocalDate birth) {
    super(id, password, enabled, login, accessToken, expirationTime);
    this.name = name;
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.currency = currency;
    this.birth = birth;
  }

  public Player() {
    super();
  }

  @Override
  public String toString() {
    return "Player{" + "name='" + name + '\'' + ", accountNumber='" + accountNumber + '\''
              + ", balance=" + balance + ", currency=" + currency + ", birth=" + birth + '}';
  }
}
