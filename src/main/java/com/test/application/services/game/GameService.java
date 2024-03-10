package com.test.application.services.game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.test.application.domain.game.Game;

public interface GameService {

  Optional<Game> gameOfId(Long id);

  Optional<Game> gameOfName(String name);

  List<Game> gamesOfReleaseDate(LocalDate releaseDate);

  List<Game> gamesOfPrice(BigDecimal price);

  List<Game> games();

  Game create(Game game);

  Game update(Game game);

  void delete(Long id);
}
