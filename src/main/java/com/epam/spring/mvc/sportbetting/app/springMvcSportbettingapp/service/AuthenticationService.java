package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.dto.UserDto;

import java.util.UUID;

public interface AuthenticationService {
  boolean isRegistered(UserDto userDto);

  UUID saveCredentials(UserDto user);
}
