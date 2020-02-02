package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.dto.HomeScreenInputDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Player;

public interface PlayerConvertor {
  Player convert(HomeScreenInputDto homeScreenInputDto, Player player);
}
