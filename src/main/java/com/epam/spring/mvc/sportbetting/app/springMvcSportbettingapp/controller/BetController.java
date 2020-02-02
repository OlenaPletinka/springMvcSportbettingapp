package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.controller;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutcomeOdd;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.model.ChoiceDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.BetTempDataService;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.DataPreparationService;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.InputValidator;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

@RequestMapping(value = "/bet")
@Controller
public class BetController {
  @Autowired
  private InputValidator inputValidator;
  @Autowired
  private BetTempDataService betTempDataService;
  @Autowired
  private DataPreparationService dataPreparationService;
  @Autowired
  private UserServise userServise;

  @RequestMapping(value = "/chooseBet", method = RequestMethod.POST)
  public String validate(@ModelAttribute("choiceDto") ChoiceDto choiceDto, Model model) {
    String choice = choiceDto.getChoice();
    String result;
    if (!inputValidator.validateFirstBetOn(choice)) {
      return "madeCorrectChoice";
    }
    if (choice.equals("q")) {
      OutcomeOdd winner = dataPreparationService.getWinner();
      BigDecimal sum = userServise.calculateGain(winner.getId(), winner.getValue());
      dataPreparationService.finishGame();
      String outcomeOdd = winner.toString();
      model.addAttribute("outcomeOdd", outcomeOdd);
      model.addAttribute("sum", sum);
      model.addAttribute("currency", "USD");
      result = "result";
    } else {
      betTempDataService.saveChoice(Integer.parseInt(choice));
      result = "betSize";
    }
    return result;
  }

  @RequestMapping(value = "/repeatedBet", method = RequestMethod.GET)
  public String repeatedBet(Model model) {
    return "betSize";
  }
}
