package com.test.game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.UseCase;
import com.test.domain.game.GameLibrary;

@Component
public class GetGamesUseCase extends UseCase<GetGamesUseCase.In, List<GetGamesUseCase.Out>> {

  private final GameLibrary gameLibrary;

  public GetGamesUseCase(GameLibrary gameLibrary) {
    this.gameLibrary = gameLibrary;
  }

  @Override
  public List<Out> execute(In in) {
    return this.gameLibrary.games()
        .stream()
        .map(game -> new Out(
            game.getId().value(),
            game.getName().value(),
            game.getReleaseDate().value(),
            game.getPrice().value()))
        .toList();
  }

  public record In() {
  }

  public record Out(Long id, String name, LocalDate releaseDate, BigDecimal price) {
  }
}
