package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;


import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.FootballSportEvent;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Outcome;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutcomeOdd;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutputBetObject;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository.FootballSportEventRepository;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class DataPreparationServiceImpl implements DataPreparationService {
  @Autowired
  WagerService wagerService;
  @Autowired
  private OutcomeService outcomeService;
  @Autowired
  private OutComeOddService outComeOddService;
  @Autowired
  private FootballSportEventRepository footballEventRepository;
  @Autowired
  private BetTempDataService betTempDataService;

  @Override
  public List<OutputBetObject> getOutputBetObjects() {
    return new ArrayList<>(Arrays.asList(
              getOutputBetObject(1L),
              getOutputBetObject(2L),
              getOutputBetObject(3L)
    ));
  }

  private OutputBetObject getOutputBetObject(long l) {
    return createOutputBetObject(getSportEventTitle(), getOutcome(l), getOutcomeOdd(l));
  }

  @Transactional
  @Override
  public OutcomeOdd getWinner() {
    Integer count = outComeOddService.getCount();
    Integer min = 1;
    Random rand = new Random();
    Long winnerId = count == 1 ? 1L : (long) (rand.nextInt(count - min + 1) + min);
    return outComeOddService.findById(winnerId);
  }

  @Override
  public void finishGame() {
    wagerService.deleteAll();
    betTempDataService.updateForGameFinish();

  }

  private OutcomeOdd getOutcomeOdd(Long id) {
    return outComeOddService.findById(id);
  }

  private Outcome getOutcome(Long id) {
    Outcome outcome = outcomeService.findById(id);
    List<OutcomeOdd> outcomeOdds = new ArrayList<>();
    outcomeOdds.add(getOutcomeOdd(id));
    outcome.setOutcomeOdds(outcomeOdds);
    return outcome;
  }

  private String getSportEventTitle() {
    FootballSportEvent sportEvent = footballEventRepository.findFirst();
    return sportEvent.getTitle();
  }

  private OutputBetObject createOutputBetObject(String sportEventTitle, Outcome outcome, OutcomeOdd outcomeOdd) {

    return new OutputBetObject(sportEventTitle, outcome, outcomeOdd, outcomeOdd.getFromDate(), outcomeOdd.getToDate());
  }
}
