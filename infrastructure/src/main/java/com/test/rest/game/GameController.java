package com.test.rest.game;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

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
  public ResponseEntity<?> create(@RequestBody NewGameDTO newGameDTO) {
    try {
      CreateGameUseCase.Out output = createGameUseCase
          .execute(new CreateGameUseCase.In(newGameDTO.name(), newGameDTO.releaseDate(), newGameDTO.price()));

      return ResponseEntity.created(URI.create("/game/" + output.id())).body(output);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    return getGameByIdUseCase.execute(new GetGameByIdUseCase.In(id))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<?> getByName(@PathVariable String name) {
    return getGameByNameUseCase.execute(new GetGameByNameUseCase.In(name))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/releaseDate/{releaseDate}")
  public ResponseEntity<?> getByReleaseDate(@PathVariable LocalDate releaseDate) {
    return ResponseEntity.ok(getGamesByReleaseDateUseCase.execute(new GetGamesByReleaseDateUseCase.In(releaseDate)));
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<?> getByPrice(@PathVariable BigDecimal price) {
    return ResponseEntity.ok(getGameByPriceUseCase.execute(new GetGamesByPriceUseCase.In(price)));
  }

  @GetMapping("/all")
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(getGamesUseCase.execute(new GetGamesUseCase.In()));
  }
}
