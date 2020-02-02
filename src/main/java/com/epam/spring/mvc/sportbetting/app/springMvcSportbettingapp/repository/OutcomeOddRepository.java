package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.OutcomeOdd;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeOddRepository extends CrudRepository<OutcomeOdd, Long> {
  @Query(value = "SELECT count(*) from outcome_odd", nativeQuery = true)
  Integer getCount();
}
