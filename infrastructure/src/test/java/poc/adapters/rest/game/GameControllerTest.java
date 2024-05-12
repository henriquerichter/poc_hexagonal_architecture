package poc.adapters.rest.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import poc.adapters.dtos.CreatedGameDTO;
import poc.adapters.dtos.NewGameDTO;
import poc.ports.in.game.GameControllerIn;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GameControllerTest {

    @InjectMocks
    private GameController gameController;
    @Mock
    private GameControllerIn gameControllerIn;

    private NewGameDTO newGameDTO;

    @BeforeEach
    public void setUp() {
        this.newGameDTO = new NewGameDTO("Game", LocalDate.of(2024, 5, 11), BigDecimal.TEN);

        when(this.gameControllerIn.createGame(anyString(), any(LocalDate.class), any(BigDecimal.class)))
                .thenReturn(new CreatedGameDTO(1L, this.newGameDTO.name(), this.newGameDTO.releaseDate(),
                        this.newGameDTO.price()));

        when(this.gameControllerIn.getGameById(anyLong())).thenReturn(Optional.of("""
                {
                    "id": 1,
                    "name": "Game",
                    "releaseDate": "2024-05-10",
                    "price": 10
                }
                """));

        when(this.gameControllerIn.getGameByName(anyString())).thenReturn(Optional.of("""
                {
                    "id": 1,
                    "name": "Game",
                    "releaseDate": "2024-05-10",
                    "price": 10
                }
                """));

        when(this.gameControllerIn.getGameByReleaseDate(any(LocalDate.class))).thenReturn("""
                {
                    "id": 1,
                    "name": "Game",
                    "releaseDate": "2024-05-10",
                    "price": 10
                }
                """);

        when(this.gameControllerIn.getGamesByPrice(any(BigDecimal.class))).thenReturn("""
                {
                    "id": 1,
                    "name": "Game",
                    "releaseDate": "2024-05-10",
                    "price": 10
                }
                """);

        when(this.gameControllerIn.getAll()).thenReturn("""
                {
                    "id": 1,
                    "name": "Game",
                    "releaseDate": "2024-05-10",
                    "price": 10
                }
                """);
    }

    @Test
    void givenNewGameDTO_whenCreate_thenReturnCreatedGame() {
        // given
        NewGameDTO newGameDTO = this.newGameDTO;

        // when
        ResponseEntity<String> actualResponse = this.gameController.create(newGameDTO);

        // then
        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals("""
                {"id":1,"name":"Game","releaseDate":"2024-05-11","price":10}""", actualResponse.getBody());
    }

    @Test
    void givenInvalidNewGameDTO_whenCreate_thenReturnUnprocessableEntity() {
        // given
        NewGameDTO newGameDTO = this.newGameDTO;
        when(this.gameControllerIn.createGame(any(String.class), any(LocalDate.class), any(BigDecimal.class))).thenThrow(new IllegalArgumentException());

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
        ResponseEntity<String> actualResponse = this.gameController.getById(gameId);

        // then
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("""
                {
                    "id": 1,
                    "name": "Game",
                    "releaseDate": "2024-05-10",
                    "price": 10
                }
                """, actualResponse.getBody());
    }

    @Test
    void givenInvalidGameId_whenGetById_thenReturnNotFound() {
        // given
        Long gameId = -1L;
        when(this.gameControllerIn.getGameById(anyLong())).thenReturn(Optional.empty());

        // when
        ResponseEntity<String> actualResponse = this.gameController.getById(gameId);

        // then
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    @Test
    void givenGameName_whenGetByName_thenReturnGame() {
        // given
        String gameName = this.newGameDTO.name();

        // when
        ResponseEntity<String> actualResponse = this.gameController.getByName(gameName);

        // then
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("""
                {
                    "id": 1,
                    "name": "Game",
                    "releaseDate": "2024-05-10",
                    "price": 10
                }
                """, actualResponse.getBody());
    }

    @Test
    void givenInvalidGameName_whenGetByName_thenReturnNotFound() {
        // given
        String gameName = null;
        when(this.gameControllerIn.getGameByName(anyString())).thenReturn(Optional.empty());

        // when
        ResponseEntity<String> actualResponse = this.gameController.getByName(gameName);

        // then
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    @Test
    void givenReleaseDate_whenGetByReleaseDate_thenReturnGames() {
        // given
        LocalDate releaseDate = this.newGameDTO.releaseDate();

        // when
        ResponseEntity<String> actualResponse = this.gameController.getByReleaseDate(releaseDate);

        // then
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("""
                {
                    "id": 1,
                    "name": "Game",
                    "releaseDate": "2024-05-10",
                    "price": 10
                }
                """, actualResponse.getBody());
    }

    @Test
    void givenPrice_whenGetByPrice_thenReturnGames() {
        // given
        BigDecimal price = this.newGameDTO.price();

        // when
        ResponseEntity<String> actualResponse = this.gameController.getByPrice(price);

        // then
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("""
                {
                    "id": 1,
                    "name": "Game",
                    "releaseDate": "2024-05-10",
                    "price": 10
                }
                """, actualResponse.getBody());
    }

    @Test
    void whenGetAll_thenReturnGames() {
        // when
        ResponseEntity<String> actualResponse = this.gameController.getAll();

        // then
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("""
                {
                    "id": 1,
                    "name": "Game",
                    "releaseDate": "2024-05-10",
                    "price": 10
                }
                """, actualResponse.getBody());
    }
}
