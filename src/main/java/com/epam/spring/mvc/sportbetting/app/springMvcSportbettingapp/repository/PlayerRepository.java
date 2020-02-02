package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Player;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
  @Query(value = "SELECT balance FROM player WHERE id = :playerId", nativeQuery = true)
  BigDecimal findBalanceById(Long playerId);

  @Modifying
  @Query(value = "UPDATE player SET balance = :newBalance WHERE id = :id", nativeQuery = true)
  void updateBalance(@Param("id") Long id, @Param("newBalance") BigDecimal newBalance);

  Player findByLogin(String login);

  @Query(value = "SELECT * FROM player WHERE access_token = :token", nativeQuery = true)
  Player findPlayerByToken(@Param("token") String token);
}

