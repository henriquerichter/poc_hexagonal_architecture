package poc.application.game;

import poc.application.UseCase;
import poc.ports.game.GameDatabase;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class GetGameByNameUseCase extends UseCase<GetGameByNameUseCase.In, Optional<GetGameByNameUseCase.Out>> {

    private final GameDatabase gameDatabase;

    public GetGameByNameUseCase(GameDatabase gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    @Override
    public Optional<Out> execute(In in) {
        return this.gameDatabase.gameOfName(in.name())
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
