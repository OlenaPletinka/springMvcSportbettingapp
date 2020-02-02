package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Outcome;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository.OutcomeRepository;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutcomeServiceImpl implements OutcomeService {
  @Autowired
  private OutcomeRepository outcomeRepository;

  @Override
  public Outcome findById(Long id) {
    return outcomeRepository.findById(id).orElseThrow(IllegalStateException::new); //always present
  }
}
