package com.test.game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.UseCase;
import com.test.domain.game.GameService;

@Component
public class GetGamesUseCase extends UseCase<GetGamesUseCase.In, List<GetGamesUseCase.Out>> {

  private final GameService gameService;

  public GetGamesUseCase(GameService gameService) {
    this.gameService = gameService;
  }

  @Override
  public List<Out> execute(In in) {
    return gameService.games()
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
