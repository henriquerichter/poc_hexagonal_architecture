package poc.application.game;

import org.springframework.stereotype.Component;
import poc.application.UseCase;
import poc.ports.out.game.GameDatabase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class GetGameByIdUseCase extends UseCase<GetGameByIdUseCase.In, Optional<GetGameByIdUseCase.Out>> {

    private final GameDatabase gameDatabase;

    public GetGameByIdUseCase(GameDatabase gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    @Override
    public Optional<Out> execute(In input) {
        return this.gameDatabase.gameOfId(input.id())
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
