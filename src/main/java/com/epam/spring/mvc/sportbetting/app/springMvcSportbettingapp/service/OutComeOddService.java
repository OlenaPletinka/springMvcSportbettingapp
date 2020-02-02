package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutcomeOdd;

public interface OutComeOddService {
  OutcomeOdd findById(Long id);

  Integer getCount();
}
