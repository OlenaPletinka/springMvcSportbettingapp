package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutcomeOdd;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutputBetObject;

import java.util.List;

public interface DataPreparationService {

  List<OutputBetObject> getOutputBetObjects();

  OutcomeOdd getWinner();

  void finishGame();
}
