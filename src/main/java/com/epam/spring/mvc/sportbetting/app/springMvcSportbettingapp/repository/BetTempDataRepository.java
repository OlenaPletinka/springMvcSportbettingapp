package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.BetTempData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BetTempDataRepository extends CrudRepository<BetTempData, Long> {

  @Modifying
  @Query(value = "UPDATE bet_temp_data SET player_id = :player_id WHERE id = 1", nativeQuery = true)
  void savePlayer(@Param("player_id") Long player_id);

  @Modifying
  @Query(value = "UPDATE bet_temp_data SET choice = :choice WHERE id = 1", nativeQuery = true)
  void saveChoice(@Param("choice") Integer choice);

  @Modifying
  @Query(value = "UPDATE bet_temp_data SET sum = :sum WHERE id = 1", nativeQuery = true)
  void saveSum(@Param("sum") BigDecimal sum);

  @Query(value = "SELECT player_id FROM bet_temp_data WHERE id = 1", nativeQuery = true)
  Long findPlayerId();

  @Modifying
  @Query(value = "UPDATE bet_temp_data SET choice = null, player_id = null, sum = null WHERE id = 1",
            nativeQuery = true)
  void updateAfterWagerCreating();
}
