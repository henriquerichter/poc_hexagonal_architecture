package com.test.game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.test.UseCase;
import com.test.domain.game.GameLibrary;

@Component
public class GetGameByNameUseCase extends UseCase<GetGameByNameUseCase.In, Optional<GetGameByNameUseCase.Out>> {

  private final GameLibrary gameLibrary;

  public GetGameByNameUseCase(GameLibrary gameLibrary) {
    this.gameLibrary = gameLibrary;
  }

  @Override
  public Optional<Out> execute(In in) {
    return this.gameLibrary.gameOfName(in.name())
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
