package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.dto.HomeScreenInputDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Player;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.enums.Currency;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.PlayerConvertor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PlayerConvertorImpl implements PlayerConvertor {
  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  @Override
  public Player convert(HomeScreenInputDto dto, Player player) {
    player.setName(dto.getName());
    player.setBirth(LocalDate.parse(dto.getBirth(), formatter));
    player.setAccountNumber(dto.getAccountNumber());
    player.setCurrency(Currency.valueOf(dto.getCurrency()));
    player.setBalance(new BigDecimal(dto.getBalance()));
    return player;
  }
}
