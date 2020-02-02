package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.model;

import lombok.Data;

@Data
public class HomeScreenInputDto {
  private String name;
  private String birth;
  private String accountNumber;
  private String currency;
  private String balance;
}
