package com.test.services.game;

import com.test.domain.game.Game;
import com.test.domain.game.GameSaver;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class GameSaveService implements GameSaver {

    private final GameStorageService gameStorageService;
    private final GameDatabaseService gameDatabaseService;

    public GameSaveService(GameStorageService gameStorageService, GameDatabaseService gameDatabaseService) {
        this.gameStorageService = gameStorageService;
        this.gameDatabaseService = gameDatabaseService;
    }

    @Override
    @Transactional
    public Game saveGame(String name, LocalDate releaseDate, BigDecimal price) {
        if (this.gameDatabaseService.gameOfName(name).isPresent()) {
            throw new IllegalArgumentException("Game with name " + name + " already exists");
        }

        Game createdGame = this.gameDatabaseService.create(new Game(name, releaseDate, price));

        this.gameStorageService.save("composter", createdGame.getId().value() + ".txt", createdGame.toJson());

        return createdGame;
    }
}
