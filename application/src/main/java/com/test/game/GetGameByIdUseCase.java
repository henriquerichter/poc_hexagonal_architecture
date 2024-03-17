package com.test.game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.test.UseCase;
import com.test.domain.game.GameService;

@Component
public class GetGameByIdUseCase extends UseCase<GetGameByIdUseCase.In, Optional<GetGameByIdUseCase.Out>> {

  private final GameService gameService;

  public GetGameByIdUseCase(GameService gameService) {
    this.gameService = gameService;
  }

  @Override
  public Optional<Out> execute(In input) {
    return this.gameService.gameOfId(input.id())
        .map(game -> new Out(
            game.getId().value(),
            game.getName().value(),
            game.getReleaseDate().value(),
            game.getPrice().value()));
  }

  public record In(Long id) {
  }

  public record Out(Long id, String name, LocalDate releaseDate, BigDecimal price) {
  }
}
