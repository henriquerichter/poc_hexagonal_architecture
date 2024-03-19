package com.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.test.domain.game.Game;
import com.test.domain.game.GameLibrary;

public class CreateGameUseCaseTest {

  private CreateGameUseCase createGameUseCase;
  private GameLibrary gameLibrary;

  @BeforeEach
  void setUp() {
    this.gameLibrary = mock(GameLibrary.class);
    when(this.gameLibrary.gameOfName("Game 1")).thenReturn(Optional.empty());
    when(this.gameLibrary.create(any(Game.class)))
        .thenReturn(new Game(2L, "Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00")));
    this.createGameUseCase = new CreateGameUseCase(this.gameLibrary);
  }

  @Test
  void givenIn_whenExecute_thenReturnOut() {
    // given
    CreateGameUseCase.In in = new CreateGameUseCase.In("Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00"));

    // when
    CreateGameUseCase.Out actualOut = this.createGameUseCase.execute(in);

    // then
    assertEquals(2, actualOut.id());
    assertEquals("Game 1", actualOut.name());
    assertEquals(LocalDate.of(2021, 1, 1), actualOut.releaseDate());
    assertEquals(new BigDecimal("100.00"), actualOut.price());
  }

  @Test
  void givenInWithExistingGameName_whenExecute_thenThrowIllegalArgumentException() {
    // given
    when(this.gameLibrary.gameOfName("Game 1"))
        .thenReturn(Optional.of(new Game(1L, "Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00"))));
    CreateGameUseCase.In in = new CreateGameUseCase.In("Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00"));

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
      this.createGameUseCase.execute(in);
    });

    // then
    assertEquals("Game with name Game 1 already exists", illegalArgumentException.getMessage());
  }
}
