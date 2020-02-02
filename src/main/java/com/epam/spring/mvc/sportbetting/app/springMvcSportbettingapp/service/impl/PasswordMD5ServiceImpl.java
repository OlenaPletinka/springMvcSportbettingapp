package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.PasswordMD5Service;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordMD5ServiceImpl implements PasswordMD5Service {
  @Override
  public String codePassword(String password) {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e);
    }
    byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

    StringBuilder sb = new StringBuilder();
    for (byte b : hashInBytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}
