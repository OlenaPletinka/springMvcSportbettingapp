package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.model.HomeScreenInputDto;

import java.math.BigDecimal;

public interface InputValidator {

  boolean validateFirstBetOn(String firstBetOn);

  boolean isValidBet(BigDecimal balance, BigDecimal firstBet);

  boolean validate(HomeScreenInputDto homeScreenInputDto);
}
