package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.model.HomeScreenInputDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.InputValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.enums.Regex.*;


@Service
public class InputValidatorImpl implements InputValidator {

  @Override
  public boolean validate(HomeScreenInputDto homeScreenInputDto) {
    return isValidName(homeScreenInputDto.getName()) && isvValidAccountNumber(homeScreenInputDto.getAccountNumber())
              && isvValidBalanceInput(homeScreenInputDto.getBalance())
              && isValidBirthInput(homeScreenInputDto.getBirth());
  }

  @Override
  public boolean validateFirstBetOn(String firstBetOn) {
    return firstBetOn.matches(BET_ON_REGEX.getValue()) || firstBetOn.equals("q");
  }

  @Override
  public boolean isValidBet(BigDecimal balance, BigDecimal bet) {
    return balance.compareTo(bet) >= 0;
  }

  private boolean isValidName(String name) {
    return name.matches(NAME_REGEX.getValue());
  }

  private boolean isvValidAccountNumber(String accountNumber) {
    return accountNumber.matches(ACCOUNT_NUMBER_REGEX.getValue());
  }

  private boolean isvValidBalanceInput(String balanceInput) {
    return balanceInput.matches(BALANCE_INPUT_REGEX.getValue());
  }

  private boolean isValidBirthInput(String birthInput) {
    return birthInput.matches(BIRTH_INPUT_REGEX.getValue());
  }

}
