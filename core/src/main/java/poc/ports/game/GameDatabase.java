package poc.ports.game;

import poc.domain.game.Game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GameDatabase {

    Optional<Game> gameOfId(Long id);

    Optional<Game> gameOfName(String name);

    List<Game> gamesOfReleaseDate(LocalDate releaseDate);

    List<Game> gamesOfPrice(BigDecimal price);

    List<Game> games();

    Game save(Game game);

    Game update(Game game);

    void delete(Long id);
}
