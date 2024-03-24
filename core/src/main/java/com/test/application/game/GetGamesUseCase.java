package com.test.application.game;

import com.test.application.UseCase;
import com.test.ports.game.GameDatabase;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class GetGamesUseCase extends UseCase<GetGamesUseCase.In, List<GetGamesUseCase.Out>> {

    private final GameDatabase gameDatabase;

    public GetGamesUseCase(GameDatabase gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    @Override
    public List<Out> execute(In in) {
        return this.gameDatabase.games()
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
