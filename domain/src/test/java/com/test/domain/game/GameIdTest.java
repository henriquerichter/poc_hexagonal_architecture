package com.test.domain.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GameIdTest {

  @Test
  void givenGameId_whenInstantiated_thenGettersReturnCorrectValues() {
    // given
    Long gameId = 1L;

    // when
    GameId actualGameId = new GameId(gameId);

    // then
    assertEquals(gameId, actualGameId.value());
  }

  @Test
  void givenNegativeGameId_whenInstantiated_thenThrowIllegalArgumentException() {
    // given
    Long gameId = -1L;

    // when
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new GameId(gameId));

    // then
    assertEquals("Game ID cannot be null or negative", exception.getMessage());
  }
}
