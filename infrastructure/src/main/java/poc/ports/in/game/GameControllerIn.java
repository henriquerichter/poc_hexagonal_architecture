package poc.ports.in.game;

import org.springframework.stereotype.Component;
import poc.adapters.dtos.CreatedGameDTO;
import poc.adapters.json.To;
import poc.application.game.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GameControllerIn {

    private final CreateGameUseCase createGameUseCase;
    private final GetGameByIdUseCase getGameByIdUseCase;
    private final GetGameByNameUseCase getGameByNameUseCase;
    private final GetGamesByReleaseDateUseCase getGamesByReleaseDateUseCase;
    private final GetGamesByPriceUseCase getGamesByPriceUseCase;
    private final GetGamesUseCase getGamesUseCase;

    public GameControllerIn(CreateGameUseCase createGameUseCase, GetGameByIdUseCase getGameByIdUseCase,
                            GetGameByNameUseCase getGameByNameUseCase, GetGamesByReleaseDateUseCase getGamesByReleaseDateUseCase,
                            GetGamesByPriceUseCase getGamesByPriceUseCase, GetGamesUseCase getGamesUseCase) {

        this.createGameUseCase = createGameUseCase;
        this.getGameByIdUseCase = getGameByIdUseCase;
        this.getGameByNameUseCase = getGameByNameUseCase;
        this.getGamesByReleaseDateUseCase = getGamesByReleaseDateUseCase;
        this.getGamesByPriceUseCase = getGamesByPriceUseCase;
        this.getGamesUseCase = getGamesUseCase;
    }

    public CreatedGameDTO createGame(String name, LocalDate releaseDate, BigDecimal price) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(releaseDate, "releaseDate cannot be null");
        Objects.requireNonNull(price, "price cannot be null");

        CreateGameUseCase.In in = new CreateGameUseCase.In(name, releaseDate, price);

        CreateGameUseCase.Out out = createGameUseCase.execute(in);

        return new CreatedGameDTO(out.id(), out.name(), out.releaseDate(), out.price());
    }

    public Optional<String> getGameById(Long id) {
        Objects.requireNonNull(id, "id cannot be null");

        GetGameByIdUseCase.In in = new GetGameByIdUseCase.In(id);

        Optional<GetGameByIdUseCase.Out> out = getGameByIdUseCase.execute(in);

        return out.map(To::json);
    }

    public Optional<String> getGameByName(String name) {
        Objects.requireNonNull(name, "name cannot be null");

        GetGameByNameUseCase.In in = new GetGameByNameUseCase.In(name);

        Optional<GetGameByNameUseCase.Out> out = this.getGameByNameUseCase.execute(in);

        return out.map(To::json);
    }

    public String getGameByReleaseDate(LocalDate releaseDate) {
        Objects.requireNonNull(releaseDate, "releaseDate cannot be null");

        GetGamesByReleaseDateUseCase.In in = new GetGamesByReleaseDateUseCase.In(releaseDate);

        List<GetGamesByReleaseDateUseCase.Out> out = this.getGamesByReleaseDateUseCase.execute(in);

        return To.json(out);
    }

    public String getGamesByPrice(BigDecimal price) {
        Objects.requireNonNull(price, "price cannot be null");

        GetGamesByPriceUseCase.In in = new GetGamesByPriceUseCase.In(price);

        List<GetGamesByPriceUseCase.Out> out = this.getGamesByPriceUseCase.execute(in);

        return To.json(out);
    }

    public String getAll() {
        GetGamesUseCase.In in = new GetGamesUseCase.In();

        List<GetGamesUseCase.Out> out = this.getGamesUseCase.execute(in);

        return To.json(out);
    }
}
