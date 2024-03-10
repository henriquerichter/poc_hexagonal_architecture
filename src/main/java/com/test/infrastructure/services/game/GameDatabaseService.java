package com.test.infrastructure.services.game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.test.application.domain.game.Game;
import com.test.application.services.game.GameService;
import com.test.infrastructure.jpa.entities.GameEntity;
import com.test.infrastructure.jpa.repositories.GameJpaRepository;

import jakarta.transaction.Transactional;

@Component
public class GameDatabaseService implements GameService {

  private final GameJpaRepository gameJpaRepository;

  public GameDatabaseService(GameJpaRepository gameJpaRepository) {
    this.gameJpaRepository = gameJpaRepository;
  }

  @Override
  public Optional<Game> gameOfId(Long id) {
    Objects.requireNonNull(id, "id cannot be null");
    return gameJpaRepository.findById(id)
        .map(GameEntity::toGame);
  }

  @Override
  public Optional<Game> gameOfName(String name) {
    Objects.requireNonNull(name, "name cannot be null");
    return gameJpaRepository.findByName(name)
        .map(GameEntity::toGame);
  }

  @Override
  public List<Game> gamesOfReleaseDate(LocalDate releaseDate) {
    Objects.requireNonNull(releaseDate, "releaseDate cannot be null");
    return gameJpaRepository.findByReleaseDate(releaseDate)
        .stream()
        .map(GameEntity::toGame)
        .toList();
  }

  @Override
  public List<Game> gamesOfPrice(BigDecimal price) {
    Objects.requireNonNull(price, "price cannot be null");
    return gameJpaRepository.findByPrice(price)
        .stream()
        .map(GameEntity::toGame)
        .toList();
  }

  @Override
  public List<Game> games() {
    return StreamSupport.stream(
        gameJpaRepository.findAll().spliterator(), true)
        .map(GameEntity::toGame)
        .toList();
  }

  @Override
  @Transactional
  public Game create(Game game) {
    Objects.requireNonNull(game, "game cannot be null");
    return gameJpaRepository.save(GameEntity.of(game)).toGame();
  }

  @Override
  @Transactional
  public Game update(Game game) {
    Objects.requireNonNull(game, "game cannot be null");
    return gameJpaRepository.save(GameEntity.of(game)).toGame();
  }

  @Override
  @Transactional
  public void delete(Long id) {
    Objects.requireNonNull(id, "id cannot be null");
    gameJpaRepository.deleteById(id);
  }
}
