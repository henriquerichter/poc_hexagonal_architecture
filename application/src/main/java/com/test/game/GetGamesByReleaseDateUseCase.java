package com.test.game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.UseCase;
import com.test.domain.game.GameService;

@Component
public class GetGamesByReleaseDateUseCase
    extends UseCase<GetGamesByReleaseDateUseCase.In, List<GetGamesByReleaseDateUseCase.Out>> {

  private final GameService gameService;

  public GetGamesByReleaseDateUseCase(GameService gameService) {
    this.gameService = gameService;
  }

  @Override
  public List<Out> execute(In in) {
    return this.gameService.gamesOfReleaseDate(in.releaseDate())
        .stream()
        .map(game -> new Out(
            game.getId().value(),
            game.getName().value(),
            game.getReleaseDate().value(),
            game.getPrice().value()))
        .toList();
  }

  public record In(LocalDate releaseDate) {
  }

  public record Out(Long id, String name, LocalDate releaseDate, BigDecimal price) {
  }
}
