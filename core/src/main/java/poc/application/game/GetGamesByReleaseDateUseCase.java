package poc.application.game;

import org.springframework.stereotype.Component;
import poc.application.UseCase;
import poc.ports.out.game.IGameDatabaseOut;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class GetGamesByReleaseDateUseCase extends UseCase<GetGamesByReleaseDateUseCase.In, List<GetGamesByReleaseDateUseCase.Out>> {

    private final IGameDatabaseOut gameDatabaseOut;

    public GetGamesByReleaseDateUseCase(IGameDatabaseOut gameDatabaseOut) {
        this.gameDatabaseOut = gameDatabaseOut;
    }

    @Override
    public List<Out> execute(In in) {
        return this.gameDatabaseOut.gamesOfReleaseDate(in.releaseDate())
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
