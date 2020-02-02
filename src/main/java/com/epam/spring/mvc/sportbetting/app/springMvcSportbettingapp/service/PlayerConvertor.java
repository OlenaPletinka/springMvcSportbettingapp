package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Player;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.model.HomeScreenInputDto;

public interface PlayerConvertor {
  Player convert(HomeScreenInputDto homeScreenInputDto, Player player);
}
