package com.test.game;

import com.test.UseCase;
import com.test.domain.game.Game;
import com.test.domain.game.GameSaver;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CreateGameUseCase extends UseCase<CreateGameUseCase.In, CreateGameUseCase.Out> {

    private final GameSaver gameSaver;

    public CreateGameUseCase(GameSaver gameSaver) {
        this.gameSaver = gameSaver;
    }

    @Override
    public Out execute(In input) {

        Game createdGame = this.gameSaver.saveGame(input.name(), input.releaseDate(), input.price());

        return new Out(
                createdGame.getId().value(),
                createdGame.getName().value(),
                createdGame.getReleaseDate().value(),
                createdGame.getPrice().value());
    }

    public record In(String name, LocalDate releaseDate, BigDecimal price) {
    }

    public record Out(Long id, String name, LocalDate releaseDate, BigDecimal price) {
    }
}
