package poc.application.game;

import org.springframework.stereotype.Component;
import poc.application.UseCase;
import poc.ports.out.game.IGameDatabaseOut;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class GetGameByNameUseCase extends UseCase<GetGameByNameUseCase.In, Optional<GetGameByNameUseCase.Out>> {

    private final IGameDatabaseOut gameDatabaseOut;

    public GetGameByNameUseCase(IGameDatabaseOut gameDatabaseOut) {
        this.gameDatabaseOut = gameDatabaseOut;
    }

    @Override
    public Optional<Out> execute(In in) {
        return this.gameDatabaseOut.gameOfName(in.name())
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
