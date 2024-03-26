package poc.application.game;

import poc.application.UseCase;
import poc.ports.game.GameDatabase;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class GetGamesByPriceUseCase extends UseCase<GetGamesByPriceUseCase.In, List<GetGamesByPriceUseCase.Out>> {

    private final GameDatabase gameDatabase;

    public GetGamesByPriceUseCase(GameDatabase gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    @Override
    public List<Out> execute(In input) {
        return this.gameDatabase.gamesOfPrice(input.price())
                .stream()
                .map(game -> new Out(
                        game.getId().value(),
                        game.getName().value(),
                        game.getReleaseDate().value(),
                        game.getPrice().value()))
                .toList();
    }

    public record In(BigDecimal price) {
    }

    public record Out(Long id, String name, LocalDate releaseDate, BigDecimal price) {
    }
}
