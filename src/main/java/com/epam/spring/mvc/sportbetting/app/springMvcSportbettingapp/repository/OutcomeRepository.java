package com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.repository;

import com.epam.spring.mvc.sportbetting.app.springMvcSportbettingapp.entity.Outcome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OutcomeRepository extends CrudRepository<Outcome, Long> {
  Optional<Outcome> findById(Long id);
}
