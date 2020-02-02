package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.enums;

public enum Regex {
  NAME_REGEX("^[a-zA-Z_ ]*$"), ACCOUNT_NUMBER_REGEX("^[0-9]*$"), BALANCE_INPUT_REGEX("^[0-9]+([.][0-9]*)?|[.][0-9]+$"),
  BIRTH_INPUT_REGEX("\\d{2}-\\d{2}-\\d{4}"), BET_ON_REGEX("^[0-9]*$");
  private String value;

  Regex(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
