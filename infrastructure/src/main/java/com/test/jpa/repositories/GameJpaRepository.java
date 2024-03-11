package com.test.jpa.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.test.jpa.entities.GameEntity;

public interface GameJpaRepository extends ListCrudRepository<GameEntity, Long> {

  Optional<GameEntity> findByName(String name);

  List<GameEntity> findByReleaseDate(LocalDate releaseDate);

  List<GameEntity> findByPrice(BigDecimal price);
}
