package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Player;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.model.UserDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.AuthenticationService;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.PasswordMD5Service;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  @Autowired
  private UserServise userServise;
  @Autowired
  private PasswordMD5Service passwordMD5Service;

  @Override
  public boolean isRegistered(UserDto userDto) {
    Player player = userServise.findByLogin(userDto.getLogin());
    if (Objects.nonNull(player)) {
      return check(userDto, player);
    } else {
      return false;
    }
  }

  @Override
  public UUID saveCredentials(UserDto user) {
    Player userByLogin = userServise.findByLogin(user.getLogin());
    if (!isUserNotNull(userByLogin)) {
      String codePassword = passwordMD5Service.codePassword(user.getPassword());
      UUID accessToken = UUID.randomUUID();
      LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10L);
      Player player = getPlayerWithCredentials(user, codePassword, accessToken, expirationTime);
      userServise.save(player);
      return accessToken;
    } else {
      return userByLogin.getAccessToken();
    }

  }

  private boolean isUserNotNull(Player player) {
    return Objects.nonNull(player);
  }

  private Player getPlayerWithCredentials(UserDto user, String codePassword, UUID accessToken, LocalDateTime expirationTime) {
    Player player = new Player();
    player.setLogin(user.getLogin());
    player.setExpirationTime(expirationTime);
    player.setAccessToken(accessToken);
    player.setPassword(codePassword);
    return player;
  }

  private boolean check(UserDto userDto, Player player) {
    String codePassword = passwordMD5Service.codePassword(userDto.getPassword());
    String password = player.getPassword();
    return userServise.checkIsRegistered(player) && codePassword.equals(password);
  }
}
