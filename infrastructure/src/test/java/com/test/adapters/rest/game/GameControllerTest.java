package com.test.adapters.rest.game;

import com.test.adapters.dtos.NewGameDTO;
import com.test.application.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GameControllerTest {

    @InjectMocks
    private GameController gameController;
    @Mock
    private CreateGameUseCase createGameUseCase;
    @Mock
    private GetGameByIdUseCase getGameByIdUseCase;
    @Mock
    private GetGameByNameUseCase getGameByNameUseCase;
    @Mock
    private GetGamesByReleaseDateUseCase getGamesByReleaseDateUseCase;
    @Mock
    private GetGamesByPriceUseCase getGameByPriceUseCase;
    @Mock
    private GetGamesUseCase getGamesUseCase;

    private NewGameDTO newGameDTO;

    @BeforeEach
    public void setUp() {
        this.newGameDTO = new NewGameDTO("Game", LocalDate.now(), BigDecimal.TEN);

        when(this.createGameUseCase.execute(any(CreateGameUseCase.In.class)))
                .thenReturn(new CreateGameUseCase.Out(1L, this.newGameDTO.name(), this.newGameDTO.releaseDate(),
                        this.newGameDTO.price()));

        when(this.getGameByIdUseCase.execute(any(GetGameByIdUseCase.In.class)))
                .thenReturn(Optional.of(new GetGameByIdUseCase.Out(1L, this.newGameDTO.name(), this.newGameDTO.releaseDate(),
                        this.newGameDTO.price())));

        when(this.getGameByNameUseCase.execute(any(GetGameByNameUseCase.In.class)))
                .thenReturn(Optional.of(new GetGameByNameUseCase.Out(1L, this.newGameDTO.name(), this.newGameDTO.releaseDate(),
                        this.newGameDTO.price())));

        when(this.getGamesByReleaseDateUseCase.execute(any(GetGamesByReleaseDateUseCase.In.class)))
                .thenReturn(List.of(new GetGamesByReleaseDateUseCase.Out(1L, this.newGameDTO.name(),
                        this.newGameDTO.releaseDate(), this.newGameDTO.price())));

        when(this.getGameByPriceUseCase.execute(any(GetGamesByPriceUseCase.In.class)))
                .thenReturn(List.of(new GetGamesByPriceUseCase.Out(1L, this.newGameDTO.name(), this.newGameDTO.releaseDate(),
                        this.newGameDTO.price())));

        when(this.getGamesUseCase.execute(any(GetGamesUseCase.In.class))).thenReturn(List.of(
                new GetGamesUseCase.Out(1L, this.newGameDTO.name(), this.newGameDTO.releaseDate(), this.newGameDTO.price())));
    }

    @Test
    void givenNewGameDTO_whenCreate_thenReturnCreatedGame() {
        // given
        NewGameDTO newGameDTO = this.newGameDTO;

        // when
        ResponseEntity<CreateGameUseCase.Out> actualResponse = (ResponseEntity<CreateGameUseCase.Out>) this.gameController.create(newGameDTO);

        // then
        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals(1L, actualResponse.getBody().id());
        assertEquals(newGameDTO.name(), actualResponse.getBody().name());
        assertEquals(newGameDTO.releaseDate(), actualResponse.getBody().releaseDate());
        assertEquals(newGameDTO.price(), actualResponse.getBody().price());
    }

    @Test
    void givenInvalidNewGameDTO_whenCreate_thenReturnUnprocessableEntity() {
        // given
        NewGameDTO newGameDTO = this.newGameDTO;
        when(this.createGameUseCase.execute(any(CreateGameUseCase.In.class))).thenThrow(new IllegalArgumentException());

        // when
        ResponseEntity<?> actualResponse = this.gameController.create(newGameDTO);

        // then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualResponse.getStatusCode());
    }

    @Test
    void givenGameId_whenGetById_thenReturnGame() {
        // given
        Long gameId = 1L;

        // when
        ResponseEntity<GetGameByIdUseCase.Out> actualResponse = this.gameController.getById(gameId);

        // then
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(1L, actualResponse.getBody().id());
        assertEquals(this.newGameDTO.name(), actualResponse.getBody().name());
        assertEquals(this.newGameDTO.releaseDate(), actualResponse.getBody().releaseDate());
        assertEquals(this.newGameDTO.price(), actualResponse.getBody().price());
    }

    @Test
    void givenInvalidGameId_whenGetById_thenReturnNotFound() {
        // given
        Long gameId = -1L;
        when(this.getGameByIdUseCase.execute(any(GetGameByIdUseCase.In.class))).thenReturn(Optional.empty());

        // when
        ResponseEntity<GetGameByIdUseCase.Out> actualResponse = this.gameController.getById(gameId);

        // then
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    @Test
    void givenGameName_whenGetByName_thenReturnGame() {
        // given
        String gameName = this.newGameDTO.name();

        // when
        ResponseEntity<GetGameByNameUseCase.Out> actualResponse = this.gameController.getByName(gameName);

        // then
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(1L, actualResponse.getBody().id());
        assertEquals(this.newGameDTO.name(), actualResponse.getBody().name());
        assertEquals(this.newGameDTO.releaseDate(), actualResponse.getBody().releaseDate());
        assertEquals(this.newGameDTO.price(), actualResponse.getBody().price());
    }

    @Test
    void givenInvalidGameName_whenGetByName_thenReturnNotFound() {
        // given
        String gameName = null;
        when(this.getGameByNameUseCase.execute(any(GetGameByNameUseCase.In.class))).thenReturn(Optional.empty());

        // when
        ResponseEntity<GetGameByNameUseCase.Out> actualResponse = this.gameController.getByName(gameName);

        // then
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    @Test
    void givenReleaseDate_whenGetByReleaseDate_thenReturnGames() {
        // given
        LocalDate releaseDate = this.newGameDTO.releaseDate();

        // when
        ResponseEntity<List<GetGamesByReleaseDateUseCase.Out>> actualResponse = this.gameController
                .getByReleaseDate(releaseDate);

        // then
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(1L, actualResponse.getBody().getFirst().id());
        assertEquals(this.newGameDTO.name(), actualResponse.getBody().getFirst().name());
        assertEquals(this.newGameDTO.releaseDate(), actualResponse.getBody().getFirst().releaseDate());
        assertEquals(this.newGameDTO.price(), actualResponse.getBody().getFirst().price());
    }

    @Test
    void givenPrice_whenGetByPrice_thenReturnGames() {
        // given
        BigDecimal price = this.newGameDTO.price();

        // when
        ResponseEntity<List<GetGamesByPriceUseCase.Out>> actualResponse = this.gameController.getByPrice(price);

        // then
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(1L, actualResponse.getBody().getFirst().id());
        assertEquals(this.newGameDTO.name(), actualResponse.getBody().getFirst().name());
        assertEquals(this.newGameDTO.releaseDate(), actualResponse.getBody().getFirst().releaseDate());
        assertEquals(this.newGameDTO.price(), actualResponse.getBody().getFirst().price());
    }

    @Test
    void whenGetAll_thenReturnGames() {
        // when
        ResponseEntity<List<GetGamesUseCase.Out>> actualResponse = this.gameController.getAll();

        // then
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(1L, actualResponse.getBody().getFirst().id());
        assertEquals(this.newGameDTO.name(), actualResponse.getBody().getFirst().name());
        assertEquals(this.newGameDTO.releaseDate(), actualResponse.getBody().getFirst().releaseDate());
        assertEquals(this.newGameDTO.price(), actualResponse.getBody().getFirst().price());
    }
}
