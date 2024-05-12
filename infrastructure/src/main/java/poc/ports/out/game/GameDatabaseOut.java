package poc.ports.out.game;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import poc.adapters.jpa.entities.GameEntity;
import poc.adapters.jpa.repositories.GameJpaRepository;
import poc.domain.game.Game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GameDatabaseOut implements IGameDatabaseOut {

    private final GameJpaRepository gameJpaRepository;

    public GameDatabaseOut(GameJpaRepository gameJpaRepository) {
        this.gameJpaRepository = gameJpaRepository;
    }

    @Override
    public Optional<Game> gameOfId(Long id) {
        Objects.requireNonNull(id, "id cannot be null");
        return this.gameJpaRepository.findById(id)
                .map(GameEntity::toGame);
    }

    @Override
    public Optional<Game> gameOfName(String name) {
        Objects.requireNonNull(name, "name cannot be null");
        return this.gameJpaRepository.findByName(name)
                .map(GameEntity::toGame);
    }

    @Override
    public List<Game> gamesOfReleaseDate(LocalDate releaseDate) {
        Objects.requireNonNull(releaseDate, "releaseDate cannot be null");
        return this.gameJpaRepository.findByReleaseDate(releaseDate)
                .stream()
                .map(GameEntity::toGame)
                .toList();
    }

    @Override
    public List<Game> gamesOfPrice(BigDecimal price) {
        Objects.requireNonNull(price, "price cannot be null");
        return this.gameJpaRepository.findByPrice(price)
                .stream()
                .map(GameEntity::toGame)
                .toList();
    }

    @Override
    public List<Game> games() {
        return this.gameJpaRepository.findAll()
                .stream()
                .map(GameEntity::toGame)
                .toList();
    }

    @Override
    @Transactional
    public Game save(Game game) {
        Objects.requireNonNull(game, "game cannot be null");
        return this.gameJpaRepository.save(GameEntity.of(game)).toGame();
    }

    @Override
    @Transactional
    public Game update(Game game) {
        Objects.requireNonNull(game, "game cannot be null");
        return this.gameJpaRepository.save(GameEntity.of(game)).toGame();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Objects.requireNonNull(id, "id cannot be null");
        this.gameJpaRepository.deleteById(id);
    }
}
