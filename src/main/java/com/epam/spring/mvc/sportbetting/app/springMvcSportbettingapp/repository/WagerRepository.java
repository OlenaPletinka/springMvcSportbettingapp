package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Wager;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WagerRepository extends CrudRepository<Wager, Long> {

  @Modifying
  @Query(value = "UPDATE wager SET is_processed = true WHERE id = :id", nativeQuery = true)
  void updateIsProcessed(@Param("id") Long id);

  @Modifying
  @Query(value = "UPDATE wager SET is_win = true WHERE id = :id", nativeQuery = true)
  void updateIsWin(@Param("id") Long winnerId);

  @Query(value = "SELECT * FROM wager WHERE outcome_odd_id = :winnerId", nativeQuery = true)
  List<Wager> findAllByOutcomeOddId(@Param("winnerId") Long winnerId);
}
