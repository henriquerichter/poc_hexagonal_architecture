package poc.application.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import poc.domain.game.Game;
import poc.ports.game.GameDatabase;
import poc.ports.game.GameStorage;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateGameUseCaseTest {

    private CreateGameUseCase createGameUseCase;
    private GameDatabase gameDatabase;
    private GameStorage gameStorage;

    @BeforeEach
    void setUp() {
        Game game = new Game(1L, "Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00"));
        this.gameDatabase = mock(GameDatabase.class);
        this.gameStorage = mock(GameStorage.class);
        when(this.gameDatabase.save(any(Game.class))).thenReturn(new Game(2L, "Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00")));
        doNothing().when(this.gameStorage).save("composter", game.getId().value() + ".txt", game.toJson());
        this.createGameUseCase = new CreateGameUseCase(this.gameDatabase, this.gameStorage);
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
        when(this.gameDatabase.save(any(Game.class))).thenThrow(new IllegalArgumentException("Game with name Game 1 already exists"));
        CreateGameUseCase.In in = new CreateGameUseCase.In("Game 1", LocalDate.of(2021, 1, 1), new BigDecimal("100.00"));

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> this.createGameUseCase.execute(in));

        // then
        assertEquals("Game with name Game 1 already exists", illegalArgumentException.getMessage());
    }
}
