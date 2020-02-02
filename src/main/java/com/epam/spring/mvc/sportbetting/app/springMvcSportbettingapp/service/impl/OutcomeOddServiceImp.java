package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutcomeOdd;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository.OutcomeOddRepository;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.OutComeOddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutcomeOddServiceImp implements OutComeOddService {
  @Autowired
  private OutcomeOddRepository outcomeOddRepository;

  @Override
  public OutcomeOdd findById(Long id) {
    return outcomeOddRepository.findById(id).orElseThrow(IllegalStateException::new); //always present;
  }

  @Override
  public Integer getCount() {
    return outcomeOddRepository.getCount();
  }
}
