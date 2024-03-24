package com.test.application.game;

import com.test.domain.game.Game;
import com.test.ports.game.GameDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetGameByIdUseCaseTest {

    private GetGameByIdUseCase getGameByIdUseCase;
    private GameDatabase gameDatabase;

    @BeforeEach
    void setUp() {
        this.gameDatabase = mock(GameDatabase.class);
        when(this.gameDatabase.gameOfId(1L))
                .thenReturn(Optional.of(new Game(1L, "Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00"))));
        this.getGameByIdUseCase = new GetGameByIdUseCase(this.gameDatabase);
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
