package com.test.adapters.jpa.repositories;

import com.test.adapters.jpa.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {

    Optional<GameEntity> findByName(String name);

    List<GameEntity> findByReleaseDate(LocalDate releaseDate);

    List<GameEntity> findByPrice(BigDecimal price);
}
