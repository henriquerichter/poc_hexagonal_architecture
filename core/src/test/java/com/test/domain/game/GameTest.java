package com.test.domain.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        this.game = new Game(2L, "Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00"));
    }

    @Test
    void givenGame_whenToJson_thenReturnJsonString() {
        // when
        String jsonGame = this.game.toJson();

        // then
        Assertions.assertEquals("{\"id\":2,\"name\":\"Game 1\",\"releaseDate\":\"2021-01-01\",\"price\":\"100.00\"}", jsonGame);
    }
}
