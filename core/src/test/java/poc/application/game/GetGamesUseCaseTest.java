package poc.application.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import poc.domain.game.Game;
import poc.ports.out.game.IGameDatabaseOut;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetGamesUseCaseTest {

    private GetGamesUseCase getGamesUseCase;
    private IGameDatabaseOut IGameDatabaseOut;

    @BeforeEach
    void setUp() {
        this.IGameDatabaseOut = mock(IGameDatabaseOut.class);
        when(this.IGameDatabaseOut.games()).thenReturn(List.of(
                new Game(1L, "Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("50.00")),
                new Game(2L, "Game 2", LocalDate.of(2021, 2, 1), new BigDecimal("100.00")),
                new Game(3L, "Game 3", LocalDate.of(2021, 3, 1), new BigDecimal("200.00"))));
        this.getGamesUseCase = new GetGamesUseCase(this.IGameDatabaseOut);
    }

    @Test
    void givenIn_whenExecute_thenReturnOut() {
        // given
        GetGamesUseCase.In in = new GetGamesUseCase.In();

        // when
        List<GetGamesUseCase.Out> actualOut = this.getGamesUseCase.execute(in);

        // then
        assertEquals(1, actualOut.getFirst().id());
        assertEquals("Game 1", actualOut.getFirst().name());
        assertEquals(LocalDate.of(2021, 1, 1), actualOut.getFirst().releaseDate());
        assertEquals(new BigDecimal("50.00"), actualOut.getFirst().price());

        assertEquals(2, actualOut.get(1).id());
        assertEquals("Game 2", actualOut.get(1).name());
        assertEquals(LocalDate.of(2021, 2, 1), actualOut.get(1).releaseDate());
        assertEquals(new BigDecimal("100.00"), actualOut.get(1).price());

        assertEquals(3, actualOut.get(2).id());
        assertEquals("Game 3", actualOut.get(2).name());
        assertEquals(LocalDate.of(2021, 3, 1), actualOut.get(2).releaseDate());
        assertEquals(new BigDecimal("200.00"), actualOut.get(2).price());
    }
}
