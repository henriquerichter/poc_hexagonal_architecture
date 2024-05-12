package poc.application.game;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import poc.application.UseCase;
import poc.domain.game.Game;
import poc.ports.out.game.IGameDatabaseOut;
import poc.ports.out.game.IGameStorageOut;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CreateGameUseCase extends UseCase<CreateGameUseCase.In, CreateGameUseCase.Out> {

    private final IGameDatabaseOut gameDatabaseOut;
    private final IGameStorageOut gameStorage;

    public CreateGameUseCase(IGameDatabaseOut gameDatabaseOut, IGameStorageOut gameStorage) {
        this.gameDatabaseOut = gameDatabaseOut;
        this.gameStorage = gameStorage;
    }

    @Override
    public Out execute(In input) {
        if (this.gameDatabaseOut.gameOfName(input.name()).isPresent()) {
            throw new IllegalArgumentException("Game with name " + input.name() + " already exists");
        }

        Game createdGame = save(input.name(), input.releaseDate(), input.price());

        return new Out(
                createdGame.getId().value(),
                createdGame.getName().value(),
                createdGame.getReleaseDate().value(),
                createdGame.getPrice().value());
    }

    @Transactional
    private Game save(String name, LocalDate releaseDate, BigDecimal price) {
        Game createdGame = this.gameDatabaseOut.save(new Game(name, releaseDate, price));

        String buketName = "composter";
        String fileName = createdGame.getId().value() + ".txt";
        String content = createdGame.toJson();

        this.gameStorage.save(buketName, fileName, content);

        return createdGame;
    }

    public record In(String name, LocalDate releaseDate, BigDecimal price) {
    }

    public record Out(Long id, String name, LocalDate releaseDate, BigDecimal price) {
    }
}
