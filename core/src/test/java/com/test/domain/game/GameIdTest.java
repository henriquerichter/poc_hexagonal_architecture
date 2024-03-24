package com.test.domain.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new GameId(gameId));

        // then
        Assertions.assertEquals("Game ID cannot be null or negative", exception.getMessage());
    }
}
