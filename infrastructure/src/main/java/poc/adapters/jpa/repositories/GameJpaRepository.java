package poc.adapters.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poc.adapters.jpa.entities.GameEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {

    Optional<GameEntity> findByName(String name);

    List<GameEntity> findByReleaseDate(LocalDate releaseDate);

    List<GameEntity> findByPrice(BigDecimal price);
}
