package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;

import org.springframework.stereotype.Component;

@Component
public class UserHolder {
  private Long userId;

  Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
