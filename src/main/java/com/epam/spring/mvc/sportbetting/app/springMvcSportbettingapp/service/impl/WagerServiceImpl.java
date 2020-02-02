package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.BetTempData;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutcomeOdd;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Player;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Wager;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository.WagerRepository;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.BetTempDataService;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.OutComeOddService;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.UserServise;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.WagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WagerServiceImpl implements WagerService {
  @Autowired
  private BetTempDataService betTempDataService;
  @Autowired
  private WagerRepository wagerRepository;
  @Autowired
  private UserServise userServise;
  @Autowired
  private OutComeOddService outComeOddService;
  @Autowired
  private UserHolder userHolder;

  @Override
  public void createWager() {
    BetTempData data = betTempDataService.getFirst();
    Player player = userServise.findUserById(userHolder.getUserId());
    Long choice = data.getChoice();
    OutcomeOdd outcomeOdd = outComeOddService.findById(choice);
    Wager wager = Wager.builder().amount(data.getSum()).player(player).currency(player.getCurrency())
              .outcomeOdd(outcomeOdd).build();
    wagerRepository.save(wager);

  }

  @Override
  public List<Wager> processWagers(Long winnerId) {
    setprocessed();
    wagerRepository.updateIsWin(winnerId);
    return wagerRepository.findAllByOutcomeOddId(winnerId);
  }

  @Transactional
  @Override
  public void deleteAll() {
    wagerRepository.deleteAll();
  }

  private void setprocessed() {
    long count = wagerRepository.count();
    for (int i = 0; i < count; i++) {
      wagerRepository.updateIsProcessed((long) i);
    }
  }
}
