package com.test.services.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.domain.game.Game;
import com.test.jpa.entities.GameEntity;
import com.test.jpa.repositories.GameJpaRepository;

@SpringBootTest
public class GameDatabaseServiceTest {

  @InjectMocks
  private GameDatabaseService gameDatabaseService;
  @Mock
  private GameJpaRepository gameJpaRepository;

  private GameEntity gameEntity;

  @BeforeEach
  void setUp() {
    Long id = 1L;
    this.gameEntity = new GameEntity();
    this.gameEntity.setId(id);
    this.gameEntity.setName("Game");
    this.gameEntity.setReleaseDate(LocalDate.now());
    this.gameEntity.setPrice(new BigDecimal("10.0"));

    when(this.gameJpaRepository.findById(this.gameEntity.getId())).thenReturn(Optional.of(this.gameEntity));
    when(this.gameJpaRepository.findByName(this.gameEntity.getName())).thenReturn(Optional.of(this.gameEntity));
    when(this.gameJpaRepository.findByReleaseDate(this.gameEntity.getReleaseDate()))
        .thenReturn(List.of(this.gameEntity));
    when(this.gameJpaRepository.findByPrice(this.gameEntity.getPrice())).thenReturn(List.of(this.gameEntity));
    when(this.gameJpaRepository.findAll()).thenReturn(List.of(this.gameEntity));
    when(this.gameJpaRepository.save(any(GameEntity.class))).thenReturn(this.gameEntity);
  }

  @Test
  void givenId_whenGameOfId_thenReturnGame() {
    // given
    Long id = this.gameEntity.getId();

    // when
    Optional<Game> actualGame = this.gameDatabaseService.gameOfId(id);

    // then
    assertTrue(actualGame.isPresent());
    assertEquals(this.gameEntity.getId(), actualGame.get().getId().value());
    assertEquals(this.gameEntity.getName(), actualGame.get().getName().value());
    assertEquals(this.gameEntity.getReleaseDate(), actualGame.get().getReleaseDate().value());
    assertEquals(this.gameEntity.getPrice(), actualGame.get().getPrice().value());

    verify(this.gameJpaRepository).findById(id);
  }

  @Test
  void givenNullId_whenGameOfId_thenThrowIllegalArgumentException() {
    // given
    Long id = null;

    // when
    NullPointerException nullPointerException = assertThrows(NullPointerException.class,
        () -> this.gameDatabaseService.gameOfId(id));

    // then
    assertEquals("id cannot be null", nullPointerException.getMessage());
  }

  @Test
  void givenName_whenGameOfName_thenReturnGame() {
    // given
    String name = this.gameEntity.getName();

    // when
    Optional<Game> actualGame = this.gameDatabaseService.gameOfName(name);

    // then
    assertTrue(actualGame.isPresent());
    assertEquals(this.gameEntity.getId(), actualGame.get().getId().value());
    assertEquals(this.gameEntity.getName(), actualGame.get().getName().value());
    assertEquals(this.gameEntity.getReleaseDate(), actualGame.get().getReleaseDate().value());
    assertEquals(this.gameEntity.getPrice(), actualGame.get().getPrice().value());

    verify(this.gameJpaRepository).findByName(name);
  }

  @Test
  void givenNullName_whenGameOfName_thenThrowIllegalArgumentException() {
    // given
    String name = null;

    // when
    NullPointerException nullPointerException = assertThrows(NullPointerException.class,
        () -> this.gameDatabaseService.gameOfName(name));

    // then
    assertEquals("name cannot be null", nullPointerException.getMessage());
  }

  @Test
  void givenReleaseDate_whenGamesOfReleaseDate_thenReturnGames() {
    // given
    LocalDate releaseDate = this.gameEntity.getReleaseDate();

    // when
    List<Game> actualGames = this.gameDatabaseService.gamesOfReleaseDate(releaseDate);

    // then
    assertEquals(1, actualGames.size());
    assertEquals(this.gameEntity.getId(), actualGames.getFirst().getId().value());
    assertEquals(this.gameEntity.getName(), actualGames.getFirst().getName().value());
    assertEquals(this.gameEntity.getReleaseDate(), actualGames.getFirst().getReleaseDate().value());
    assertEquals(this.gameEntity.getPrice(), actualGames.getFirst().getPrice().value());

    verify(this.gameJpaRepository).findByReleaseDate(releaseDate);
  }

  @Test
  void givenNullReleaseDate_whenGamesOfReleaseDate_thenThrowIllegalArgumentException() {
    // given
    LocalDate releaseDate = null;

    // when
    NullPointerException nullPointerException = assertThrows(NullPointerException.class,
        () -> this.gameDatabaseService.gamesOfReleaseDate(releaseDate));

    // then
    assertEquals("releaseDate cannot be null", nullPointerException.getMessage());
  }

  @Test
  void givenPrice_whenGamesOfPrice_thenReturnGames() {
    // given
    BigDecimal price = this.gameEntity.getPrice();

    // when
    List<Game> actualGames = this.gameDatabaseService.gamesOfPrice(price);

    // then
    assertEquals(1, actualGames.size());
    assertEquals(this.gameEntity.getId(), actualGames.getFirst().getId().value());
    assertEquals(this.gameEntity.getName(), actualGames.getFirst().getName().value());
    assertEquals(this.gameEntity.getReleaseDate(), actualGames.getFirst().getReleaseDate().value());
    assertEquals(this.gameEntity.getPrice(), actualGames.getFirst().getPrice().value());

    verify(this.gameJpaRepository).findByPrice(price);
  }

  @Test
  void givenNullPrice_whenGamesOfPrice_thenThrowIllegalArgumentException() {
    // given
    BigDecimal price = null;

    // when
    NullPointerException nullPointerException = assertThrows(NullPointerException.class,
        () -> this.gameDatabaseService.gamesOfPrice(price));

    // then
    assertEquals("price cannot be null", nullPointerException.getMessage());
  }

  @Test
  void whenGames_thenReturnGames() {
    // when
    List<Game> actualGames = this.gameDatabaseService.games();

    // then
    assertEquals(1, actualGames.size());
    assertEquals(this.gameEntity.getId(), actualGames.getFirst().getId().value());
    assertEquals(this.gameEntity.getName(), actualGames.getFirst().getName().value());
    assertEquals(this.gameEntity.getReleaseDate(), actualGames.getFirst().getReleaseDate().value());
    assertEquals(this.gameEntity.getPrice(), actualGames.getFirst().getPrice().value());

    verify(this.gameJpaRepository).findAll();
  }

  @Test
  void givenGame_whenCreate_thenReturnGame() {
    // given
    Game game = this.gameEntity.toGame();

    // when
    Game actualGame = this.gameDatabaseService.create(game);

    // then
    assertEquals(this.gameEntity.getId(), actualGame.getId().value());
    assertEquals(this.gameEntity.getName(), actualGame.getName().value());
    assertEquals(this.gameEntity.getReleaseDate(), actualGame.getReleaseDate().value());
    assertEquals(this.gameEntity.getPrice(), actualGame.getPrice().value());

    verify(this.gameJpaRepository).save(any(GameEntity.class));
  }

  @Test
  void givenNullGame_whenCreate_thenThrowIllegalArgumentException() {
    // given
    Game game = null;

    // when
    NullPointerException nullPointerException = assertThrows(NullPointerException.class,
        () -> this.gameDatabaseService.create(game));

    // then
    assertEquals("game cannot be null", nullPointerException.getMessage());
  }

  @Test
  void givenGame_whenUpdate_thenReturnGame() {
    // given
    Game game = this.gameEntity.toGame();

    // when
    Game actualGame = this.gameDatabaseService.update(game);

    // then
    assertEquals(this.gameEntity.getId(), actualGame.getId().value());
    assertEquals(this.gameEntity.getName(), actualGame.getName().value());
    assertEquals(this.gameEntity.getReleaseDate(), actualGame.getReleaseDate().value());
    assertEquals(this.gameEntity.getPrice(), actualGame.getPrice().value());

    verify(this.gameJpaRepository).save(any(GameEntity.class));
  }

  @Test
  void givenNullGame_whenUpdate_thenThrowIllegalArgumentException() {
    // given
    Game game = null;

    // when
    NullPointerException nullPointerException = assertThrows(NullPointerException.class,
        () -> this.gameDatabaseService.update(game));

    // then
    assertEquals("game cannot be null", nullPointerException.getMessage());
  }

  @Test
  void givenId_whenDelete_thenDeleteGame() {
    // given
    Long id = this.gameEntity.getId();

    // when
    this.gameDatabaseService.delete(id);

    // then
    verify(this.gameJpaRepository).deleteById(id);
  }

  @Test
  void givenNullId_whenDelete_thenThrowIllegalArgumentException() {
    // given
    Long id = null;

    // when
    NullPointerException nullPointerException = assertThrows(NullPointerException.class,
        () -> this.gameDatabaseService.delete(id));

    // then
    assertEquals("id cannot be null", nullPointerException.getMessage());
  }
}
