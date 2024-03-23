package com.test.jpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.test.domain.game.Game;

public class GameEntityTest {

  private Long id;
  private String name;
  private LocalDate releaseDate;
  private BigDecimal price;

  @BeforeEach
  void setUp() {
    id = 1L;
    name = "Test Game";
    releaseDate = LocalDate.of(2022, 1, 1);
    price = BigDecimal.valueOf(49.99);
  }

  @Test
  void givenGame_whenOf_thenGameEntityIsCreated() {
    // given
    Game game = new Game(this.name, this.releaseDate, this.price);

    // when
    GameEntity actualGameEntity = GameEntity.of(game);

    // then
    assertNull(actualGameEntity.getId());
    assertEquals(game.getName().value(), actualGameEntity.getName());
    assertEquals(game.getReleaseDate().value(), actualGameEntity.getReleaseDate());
    assertEquals(game.getPrice().value(), actualGameEntity.getPrice());
  }

  @Test
  void givenGameEntity_whenToGame_thenGameIsReturned() {
    // given
    GameEntity gameEntity = new GameEntity(this.id, this.name, this.releaseDate, this.price);

    // when
    Game actualGame = gameEntity.toGame();

    // then
    assertEquals(gameEntity.getId(), actualGame.getId().value());
    assertEquals(gameEntity.getName(), actualGame.getName().value());
    assertEquals(gameEntity.getReleaseDate(), actualGame.getReleaseDate().value());
    assertEquals(gameEntity.getPrice(), actualGame.getPrice().value());
  }
}
