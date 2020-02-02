package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.impl;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.dto.HomeScreenInputDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.dto.UserDto;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Player;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Wager;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository.PlayerRepository;
import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServise {
  @Autowired
  private PlayerConvertor playerConvertor;
  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private InputValidator inputValidator;
  @Autowired
  private BetTempDataService betTempDataService;
  @Autowired
  private WagerService wagerService;
  @Autowired
  private OutComeOddService outComeOddService;
  @Autowired
  private UserHolder userHolder;

  @Override
  public Long updatePlayer(HomeScreenInputDto dto) {
    Long id = userHolder.getUserId();
    Player player = getUpdatedPlayer(dto, id);
    playerRepository.save(player);
    betTempDataService.savePlayer(id);
    return id;
  }

  @Override
  public BigDecimal getUserBalance() {
    return playerRepository.findBalanceById(userHolder.getUserId());
  }

  @Transactional
  @Override
  public BigDecimal updateBalance(BigDecimal sum, BigDecimal balanceById) {
    BigDecimal newBalance = balanceById.subtract(sum);
    Player player = playerRepository.findById(userHolder.getUserId()).get();
    player.setBalance(newBalance);
    playerRepository.save(player);
    return newBalance;
  }

  @Override
  public Player findUserById(Long player_id) {
    Optional<Player> player = playerRepository.findById(player_id);
    return player.get();
  }

  @Transactional
  @Override
  public BigDecimal calculateGain(Long id, Double outcomeOddValue) {
    List<Wager> wagers = wagerService.processWagers(id);
    if (!wagers.isEmpty()) {
      BigDecimal gain = wagers.stream().map(Wager::getAmount).reduce(BigDecimal::add).get()
                .multiply(BigDecimal.valueOf(outcomeOddValue));
      Player player = playerRepository.findById(userHolder.getUserId()).get();
      updateBalance(gain, player.getBalance());
      return gain;
    } else {
      return BigDecimal.ZERO;
    }
  }

  @Override
  public Player findByLogin(String login) {
    return playerRepository.findByLogin(login);
  }

  @Transactional
  @Override
  public void save(Player player) {
    playerRepository.save(player);
  }

  @Override
  public UUID updateToken(UserDto user) {
    UUID accessToken = UUID.randomUUID();
    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10L);
    Player player = playerRepository.findByLogin(user.getLogin());
    player.setExpirationTime(expirationTime);
    player.setAccessToken(accessToken);
    playerRepository.save(player);
    return accessToken;
  }

  @Override
  public boolean checkIsRegistered(Player player) {
    return Objects.nonNull(player) && isContain(player);
  }

  @Override
  public Player findUserByToken(String token) {
    return playerRepository.findPlayerByToken(token);
  }

  @Override
  public void updateExpirationTime(Player player) {
    player.setExpirationTime(LocalDateTime.now());
    playerRepository.save(player);
  }

  @Override
  public BigDecimal getUserBalanceByLogin(UserDto user) {
    Player player = findByLogin(user.getLogin());
    return player.getBalance();
  }

  private boolean isContain(Player playerById) {
    return Objects.nonNull(playerById.getName()) && Objects.nonNull(playerById.getBirth())
              && Objects.nonNull(playerById.getBalance()) && Objects.nonNull(playerById.getAccountNumber()) &&
              Objects.nonNull(playerById.getCurrency());
  }

  private Player getUpdatedPlayer(HomeScreenInputDto dto, Long id) {
    Player player = findUserById(id);
    return playerConvertor.convert(dto, player);
  }
}

