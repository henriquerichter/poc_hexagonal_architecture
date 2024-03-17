package com.test.rest.game;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dtos.NewGameDTO;
import com.test.game.CreateGameUseCase;
import com.test.game.GetGameByIdUseCase;
import com.test.game.GetGameByNameUseCase;
import com.test.game.GetGamesByPriceUseCase;
import com.test.game.GetGamesByReleaseDateUseCase;
import com.test.game.GetGamesUseCase;

@RestController
@RequestMapping(value = "/game")
public class GameController {

  private final CreateGameUseCase createGameUseCase;
  private final GetGameByIdUseCase getGameByIdUseCase;
  private final GetGameByNameUseCase getGameByNameUseCase;
  private final GetGamesByReleaseDateUseCase getGamesByReleaseDateUseCase;
  private final GetGamesByPriceUseCase getGameByPriceUseCase;
  private final GetGamesUseCase getGamesUseCase;

  public GameController(CreateGameUseCase createGameUseCase, GetGameByIdUseCase getGameByIdUseCase,
      GetGameByNameUseCase getGameByNameUseCase, GetGamesByReleaseDateUseCase getGamesByReleaseDateUseCase,
      GetGamesByPriceUseCase getGameByPriceUseCase, GetGamesUseCase getGamesUseCase) {

    this.createGameUseCase = createGameUseCase;
    this.getGameByIdUseCase = getGameByIdUseCase;
    this.getGameByNameUseCase = getGameByNameUseCase;
    this.getGameByPriceUseCase = getGameByPriceUseCase;
    this.getGamesByReleaseDateUseCase = getGamesByReleaseDateUseCase;
    this.getGamesUseCase = getGamesUseCase;
  }

  @PostMapping
  @RegisterReflectionForBinding(classes = { CreateGameUseCase.Out.class }) // native
  public ResponseEntity<?> create(@RequestBody NewGameDTO newGameDTO) {
    try {
      CreateGameUseCase.In in = new CreateGameUseCase.In(newGameDTO.name(), newGameDTO.releaseDate(),
          newGameDTO.price());

      CreateGameUseCase.Out output = this.createGameUseCase.execute(in);

      return ResponseEntity.created(URI.create("/game/" + output.id())).body(output);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetGameByIdUseCase.Out> getById(@PathVariable Long id) {
    GetGameByIdUseCase.In in = new GetGameByIdUseCase.In(id);

    Optional<GetGameByIdUseCase.Out> out = this.getGameByIdUseCase.execute(in);

    return out.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<GetGameByNameUseCase.Out> getByName(@PathVariable String name) {
    GetGameByNameUseCase.In in = new GetGameByNameUseCase.In(name);

    Optional<GetGameByNameUseCase.Out> out = this.getGameByNameUseCase.execute(in);

    return out.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/releaseDate/{releaseDate}")
  public ResponseEntity<List<GetGamesByReleaseDateUseCase.Out>> getByReleaseDate(@PathVariable LocalDate releaseDate) {
    GetGamesByReleaseDateUseCase.In in = new GetGamesByReleaseDateUseCase.In(releaseDate);

    List<GetGamesByReleaseDateUseCase.Out> out = this.getGamesByReleaseDateUseCase.execute(in);

    return ResponseEntity.ok(out);
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<GetGamesByPriceUseCase.Out>> getByPrice(@PathVariable BigDecimal price) {
    GetGamesByPriceUseCase.In in = new GetGamesByPriceUseCase.In(price);

    List<GetGamesByPriceUseCase.Out> out = getGameByPriceUseCase.execute(in);

    return ResponseEntity.ok(out);
  }

  @GetMapping("/all")
  public ResponseEntity<List<GetGamesUseCase.Out>> getAll() {
    GetGamesUseCase.In in = new GetGamesUseCase.In();

    List<GetGamesUseCase.Out> out = this.getGamesUseCase.execute(in);

    return ResponseEntity.ok(out);
  }
}
