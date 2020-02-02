package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.controller;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.dto.HomeScreenInputDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.dto.SumDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutputBetObject;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping(value = "/player")
@Controller
public class PlayerController {

  @Autowired
  private UserServise userServise;
  @Autowired
  private DataPreparationService dataPreparationService;
  @Autowired
  private InputValidator inputValidator;
  @Autowired
  private BetTempDataService betTempDataService;
  @Autowired
  private WagerService wagerService;

  @RequestMapping(value = "/savePlayer", method = RequestMethod.POST)
  public String savePlayer(@ModelAttribute("/homeScreenInput") HomeScreenInputDto homeScreenInputDto, Model model) {
    if (!inputValidator.validate(homeScreenInputDto)) {
      return "invalidInput";
    } else {
      userServise.updatePlayer(homeScreenInputDto);
      List<OutputBetObject> outputBetObjects = dataPreparationService.getOutputBetObjects();

      model.addAttribute("outputBetObjects", outputBetObjects);
      model.addAttribute("balance", new BigDecimal(homeScreenInputDto.getBalance()));
      return "chooseOutcome";
    }
  }

  @RequestMapping(value = "/updateBalance", method = RequestMethod.POST)
  public String updateBalance(@ModelAttribute("homeScreenInput") SumDto sumDto, Model model) {
    BigDecimal playerBalance = userServise.getUserBalance();
    BigDecimal sum = sumDto.getSum();
    if (!inputValidator.isValidBet(playerBalance, sum)) {
      model.addAttribute("currentBalance", playerBalance);
      model.addAttribute("sum", sum);
      return "notEnoughMoney";
    } else {
      BigDecimal balance = userServise.updateBalance(sumDto.getSum(), playerBalance);
      betTempDataService.saveSum(sum);
      wagerService.createWager();
      List<OutputBetObject> outputBetObjects = dataPreparationService.getOutputBetObjects();
      model.addAttribute("outputBetObjects", outputBetObjects);
      model.addAttribute("balance", balance);
      return "chooseOutcome";
    }
  }
}
