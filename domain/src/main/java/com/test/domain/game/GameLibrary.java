package com.test.domain.game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GameLibrary {

  Optional<Game> gameOfId(Long id);

  Optional<Game> gameOfName(String name);

  List<Game> gamesOfReleaseDate(LocalDate releaseDate);

  List<Game> gamesOfPrice(BigDecimal price);

  List<Game> games();

  Game create(Game game);

  Game update(Game game);

  void delete(Long id);
}
