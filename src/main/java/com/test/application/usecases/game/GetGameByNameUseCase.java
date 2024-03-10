package com.test.application.usecases.game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.test.application.services.game.GameService;
import com.test.application.usecases.UseCase;

@Component
public class GetGameByNameUseCase extends UseCase<GetGameByNameUseCase.In, Optional<GetGameByNameUseCase.Out>> {

  private final GameService gameService;

  public GetGameByNameUseCase(GameService gameService) {
    this.gameService = gameService;
  }

  @Override
  public Optional<Out> execute(In in) {
    return gameService.gameOfName(in.name())
        .map(game -> new Out(
            game.getId().value(),
            game.getName().value(),
            game.getReleaseDate().value(),
            game.getPrice().value()));
  }

  public record In(String name) {
  }

  public record Out(Long id, String name, LocalDate releaseDate, BigDecimal price) {
  }
}
