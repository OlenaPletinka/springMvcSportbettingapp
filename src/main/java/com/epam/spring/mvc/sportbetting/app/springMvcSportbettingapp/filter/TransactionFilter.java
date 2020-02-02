package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.filter;


import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Player;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.UserServise;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
public class TransactionFilter implements Filter {
  @Autowired
  private UserServise userServise;
  @Autowired
  private UserHolder userHolder;

  @Override
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    String servletPath = req.getServletPath();
    if (!isMainPage(servletPath) && !isStatic(servletPath)) {
      if (!checkCookie(req)) {
        ((HttpServletResponse) response).sendRedirect("/");
      }
    }

    chain.doFilter(request, response);
  }

  private boolean isMainPage(String servletPath) {
    return servletPath.equals("/") || servletPath.equals("") || servletPath.equals("/logIn");
  }

  private boolean isStatic(String servletPath) {
    return servletPath.startsWith("/img/");
  }

  private boolean checkCookie(HttpServletRequest req) {
    Cookie[] cookies = req.getCookies();
    String token = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("token"))
              .findFirst().get().getValue();
    Player playerByToken = userServise.findUserByToken(token);
    if (Objects.nonNull(playerByToken)) {
      userHolder.setUserId(playerByToken.getId());
      updateExpirationTime(playerByToken);
      return true;
    } else {
      return false;
    }

  }

  private void updateExpirationTime(Player player) {
    userServise.updateExpirationTime(player);
  }

}
