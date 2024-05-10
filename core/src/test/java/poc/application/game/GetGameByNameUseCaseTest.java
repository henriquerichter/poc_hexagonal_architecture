package poc.application.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import poc.domain.game.Game;
import poc.ports.out.game.GameDatabase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetGameByNameUseCaseTest {

    private GetGameByNameUseCase getGameByNameUseCase;
    private GameDatabase gameDatabase;

    @BeforeEach
    void setUp() {
        this.gameDatabase = mock(GameDatabase.class);
        when(this.gameDatabase.gameOfName("Game 1"))
                .thenReturn(Optional.of(new Game(1L, "Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00"))));
        this.getGameByNameUseCase = new GetGameByNameUseCase(this.gameDatabase);
    }

    @Test
    void givenIn_whenExecute_thenReturnOut() {
        // given
        GetGameByNameUseCase.In in = new GetGameByNameUseCase.In("Game 1");

        // when
        Optional<GetGameByNameUseCase.Out> actualOut = this.getGameByNameUseCase.execute(in);

        // then

        assertEquals(1, actualOut.get().id());
        assertEquals("Game 1", actualOut.get().name());
        assertEquals(LocalDate.of(2021, 1, 1), actualOut.get().releaseDate());
        assertEquals(new BigDecimal("100.00"), actualOut.get().price());
    }
}
