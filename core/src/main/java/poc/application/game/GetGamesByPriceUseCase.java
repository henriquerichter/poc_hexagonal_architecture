package poc.application.game;

import org.springframework.stereotype.Component;
import poc.application.UseCase;
import poc.ports.out.game.IGameDatabaseOut;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class GetGamesByPriceUseCase extends UseCase<GetGamesByPriceUseCase.In, List<GetGamesByPriceUseCase.Out>> {

    private final IGameDatabaseOut gameDatabaseOut;

    public GetGamesByPriceUseCase(IGameDatabaseOut gameDatabaseOut) {
        this.gameDatabaseOut = gameDatabaseOut;
    }

    @Override
    public List<Out> execute(In input) {
        return this.gameDatabaseOut.gamesOfPrice(input.price())
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
