package com.test.application.game;

import com.test.application.UseCase;
import com.test.ports.game.GameDatabase;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class GetGamesByReleaseDateUseCase extends UseCase<GetGamesByReleaseDateUseCase.In, List<GetGamesByReleaseDateUseCase.Out>> {

    private final GameDatabase gameDatabase;

    public GetGamesByReleaseDateUseCase(GameDatabase gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    @Override
    public List<Out> execute(In in) {
        return this.gameDatabase.gamesOfReleaseDate(in.releaseDate())
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
