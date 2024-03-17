package com.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.test.domain.game.Game;
import com.test.domain.game.GameService;

public class GetGamesByReleaseDateUseCaseTest {

  private GetGamesByReleaseDateUseCase getGamesByReleaseDateUseCase;
  private GameService gameService;

  @BeforeEach
  void setUp() {
    this.gameService = mock(GameService.class);
    when(this.gameService.gamesOfReleaseDate(LocalDate.of(2021, 2, 1)))
        .thenReturn(List.of(new Game(2L, "Game 2", LocalDate.of(2021, 2, 1), new BigDecimal("100.00"))));
    this.getGamesByReleaseDateUseCase = new GetGamesByReleaseDateUseCase(this.gameService);
  }

  @Test
  void givenIn_whenExecute_thenReturnOut() {
    // given
    GetGamesByReleaseDateUseCase.In in = new GetGamesByReleaseDateUseCase.In(LocalDate.of(2021, 2, 1));

    // when
    List<GetGamesByReleaseDateUseCase.Out> actualOut = this.getGamesByReleaseDateUseCase.execute(in);

    // then
    assertEquals(2, actualOut.getFirst().id());
    assertEquals("Game 2", actualOut.getFirst().name());
    assertEquals(LocalDate.of(2021, 2, 1), actualOut.getFirst().releaseDate());
    assertEquals(new BigDecimal("100.00"), actualOut.getFirst().price());
  }
}
