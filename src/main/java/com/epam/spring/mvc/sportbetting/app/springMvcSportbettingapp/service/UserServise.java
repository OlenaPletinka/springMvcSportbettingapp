package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Player;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.model.HomeScreenInputDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.model.UserDto;

import java.math.BigDecimal;
import java.util.UUID;

public interface UserServise {
  Long updatePlayer(HomeScreenInputDto homeScreenInputDto);

  BigDecimal getUserBalance();

  BigDecimal updateBalance(BigDecimal sum, BigDecimal balanceById);

  Player findUserById(Long player_id);

  BigDecimal calculateGain(Long id, Double outcomeOddValue);

  Player findByLogin(String login);

  void save(Player player);

  UUID updateToken(UserDto user);

  boolean checkIsRegistered(Player player);

  Player findUserByToken(String token);

  void updateExpirationTime(Player player);

  BigDecimal getUserBalanceByLogin(UserDto user);
}
