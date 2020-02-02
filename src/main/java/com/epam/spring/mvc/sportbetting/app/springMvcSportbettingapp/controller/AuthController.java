package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.controller;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutputBetObject;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.model.UserDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.AuthenticationService;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.DataPreparationService;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AuthController {
  @Autowired
  private AuthenticationService authenticationService;
  @Autowired
  private UserServise userServise;
  @Autowired
  private DataPreparationService dataPreparationService;
  @Autowired
  private UserServise getUserServise;

  @RequestMapping(value = "/", method = GET)
  public String main(Model model) {

    return "welcome";
  }

  @RequestMapping(value = "logIn", method = POST)
  public String homeScreen(@ModelAttribute("user") UserDto user, HttpServletResponse response, Model model) {
    String result;
    if (authenticationService.isRegistered(user)) {
      UUID uuid = userServise.updateToken(user);
      response.addCookie(new Cookie("token", uuid.toString()));

      List<OutputBetObject> outputBetObjects = dataPreparationService.getOutputBetObjects();
      model.addAttribute("outputBetObjects", outputBetObjects);
      model.addAttribute("balance", userServise.getUserBalanceByLogin(user));
      result = "chooseOutcome";
    } else {
      UUID uuid = authenticationService.saveCredentials(user);
      response.addCookie(new Cookie("token", uuid.toString()));
      result = "homeScreen";
    }
    return result;
  }
}
