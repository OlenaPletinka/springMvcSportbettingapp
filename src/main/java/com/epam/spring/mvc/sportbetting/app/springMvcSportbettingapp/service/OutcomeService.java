package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Outcome;

public interface OutcomeService {
  Outcome findById(Long id);
}
