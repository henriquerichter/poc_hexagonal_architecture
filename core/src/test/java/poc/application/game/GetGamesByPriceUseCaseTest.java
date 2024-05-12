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

public class GetGamesByPriceUseCaseTest {

    private GetGamesByPriceUseCase getGamesByPriceUseCase;
    private IGameDatabaseOut IGameDatabaseOut;

    @BeforeEach
    void setUp() {
        this.IGameDatabaseOut = mock(IGameDatabaseOut.class);
        when(this.IGameDatabaseOut.gamesOfPrice(new BigDecimal("100.00")))
                .thenReturn(List.of(new Game(2L, "Game 2", LocalDate.of(2021, 2, 1), new BigDecimal("100.00"))));
        this.getGamesByPriceUseCase = new GetGamesByPriceUseCase(this.IGameDatabaseOut);
    }

    @Test
    void givenIn_whenExecute_thenReturnOut() {
        // given
        GetGamesByPriceUseCase.In in = new GetGamesByPriceUseCase.In(new BigDecimal("100.00"));

        // when
        List<GetGamesByPriceUseCase.Out> actualOut = this.getGamesByPriceUseCase.execute(in);

        // then
        assertEquals(2, actualOut.getFirst().id());
        assertEquals("Game 2", actualOut.getFirst().name());
        assertEquals(LocalDate.of(2021, 2, 1), actualOut.getFirst().releaseDate());
        assertEquals(new BigDecimal("100.00"), actualOut.getFirst().price());
    }
}
