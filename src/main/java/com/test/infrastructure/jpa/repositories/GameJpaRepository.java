package com.test.infrastructure.jpa.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.infrastructure.jpa.entities.GameEntity;

public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {

  Optional<GameEntity> findByName(String name);

  List<GameEntity> findByReleaseDate(LocalDate releaseDate);

  List<GameEntity> findByPrice(BigDecimal price);
}
