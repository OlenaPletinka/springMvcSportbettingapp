package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.BetTempData;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository.BetTempDataRepository;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.BetTempDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BetTempDataServiceImpl implements BetTempDataService {
  @Autowired
  private BetTempDataRepository betTempDataRepository;
  @Autowired
  private UserHolder userHolder;

  @Transactional
  @Override
  public void savePlayer(Long playerId) {
    betTempDataRepository.savePlayer(playerId);
  }

  @Transactional
  @Override
  public void saveSum(BigDecimal sum) {
    betTempDataRepository.saveSum(sum);
  }

  @Transactional
  @Override
  public void saveChoice(Integer choice) {
    betTempDataRepository.saveChoice(choice);
  }

  @Override
  public BetTempData getFirst() {
    Optional<BetTempData> optionalData = betTempDataRepository.findById(1L);
    return optionalData.orElseThrow(IllegalStateException::new); //always present
  }

  @Transactional
  @Override
  public void updateForGameFinish() {
    betTempDataRepository.updateAfterWagerCreating();
  }
}
