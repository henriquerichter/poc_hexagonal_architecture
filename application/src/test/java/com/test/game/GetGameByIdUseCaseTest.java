package com.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.test.domain.game.Game;
import com.test.domain.game.GameLibrary;

public class GetGameByIdUseCaseTest {

  private GetGameByIdUseCase getGameByIdUseCase;
  private GameLibrary gameLibrary;

  @BeforeEach
  void setUp() {
    this.gameLibrary = mock(GameLibrary.class);
    when(this.gameLibrary.gameOfId(1L))
        .thenReturn(Optional.of(new Game(1L, "Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00"))));
    this.getGameByIdUseCase = new GetGameByIdUseCase(this.gameLibrary);
  }

  @Test
  void givenIn_whenExecute_thenReturnOut() {
    // given
    GetGameByIdUseCase.In in = new GetGameByIdUseCase.In(1L);

    // when
    Optional<GetGameByIdUseCase.Out> actualOut = this.getGameByIdUseCase.execute(in);

    // then
    assertEquals(1, actualOut.get().id());
    assertEquals("Game 1", actualOut.get().name());
    assertEquals(LocalDate.of(2021, 1, 1), actualOut.get().releaseDate());
    assertEquals(new BigDecimal("100.00"), actualOut.get().price());
  }
}
