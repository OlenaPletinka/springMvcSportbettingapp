package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table
public class BetTempData {
  @Id
  private Long id;

  @Column
  private Long player_id;

  @Column
  private BigDecimal sum;

  @Column
  private Long choice;
}
