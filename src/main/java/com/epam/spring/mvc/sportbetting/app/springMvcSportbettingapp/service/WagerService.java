package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Wager;

import java.util.List;

public interface WagerService {
  void createWager();

  List<Wager> processWagers(Long winnerId);

  void deleteAll();
}
