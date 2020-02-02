package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.FootballSportEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballSportEventRepository extends CrudRepository<FootballSportEvent, Long> {
  @Query(value = "SELECT * FROM football_sport_event LIMIT 1", nativeQuery = true)
  FootballSportEvent findFirst();
}
