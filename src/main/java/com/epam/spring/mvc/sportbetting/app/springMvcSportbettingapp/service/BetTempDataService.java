package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.BetTempData;

import java.math.BigDecimal;

public interface BetTempDataService {
  void savePlayer(Long playerId);

  void saveSum(BigDecimal sum);

  void saveChoice(Integer choice);

  BetTempData getFirst();

  void updateForGameFinish();
}
