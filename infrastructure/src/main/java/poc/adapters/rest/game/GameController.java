package poc.adapters.rest.game;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poc.adapters.dtos.CreatedGameDTO;
import poc.adapters.dtos.NewGameDTO;
import poc.ports.in.game.GameControllerIn;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    private final GameControllerIn gameControllerIn;

    public GameController(GameControllerIn gameControllerIn) {
        this.gameControllerIn = gameControllerIn;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody NewGameDTO newGameDTO) {
        try {
            CreatedGameDTO out = this.gameControllerIn.createGame(newGameDTO.name(), newGameDTO.releaseDate(), newGameDTO.price());

            return ResponseEntity.created(URI.create("/game/" + out.id())).body(out.toString());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getById(@PathVariable Long id) {
        Optional<String> out = this.gameControllerIn.getGameById(id);

        return out.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getByName(@PathVariable String name) {
        Optional<String> out = this.gameControllerIn.getGameByName(name);

        return out.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/releaseDate/{releaseDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getByReleaseDate(@PathVariable LocalDate releaseDate) {
        String out = this.gameControllerIn.getGameByReleaseDate(releaseDate);

        return ResponseEntity.ok(out);
    }

    @GetMapping(value = "/price/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getByPrice(@PathVariable BigDecimal price) {
        String out = this.gameControllerIn.getGamesByPrice(price);

        return ResponseEntity.ok(out);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll() {
        String out = this.gameControllerIn.getAll();

        return ResponseEntity.ok(out);
    }
}
