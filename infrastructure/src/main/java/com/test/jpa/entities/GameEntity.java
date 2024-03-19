package com.test.jpa.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.lang.NonNull;

import com.test.domain.game.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "game")
public class GameEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private LocalDate releaseDate;
  private BigDecimal price;

  public GameEntity() {
  }

  public GameEntity(Long id, String name, LocalDate releaseDate, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.releaseDate = releaseDate;
    this.price = price;
  }

  public GameEntity(String name, LocalDate releaseDate, BigDecimal price) {
    this.name = name;
    this.releaseDate = releaseDate;
    this.price = price;
  }

  public static @NonNull GameEntity of(Game game) {
    return new GameEntity(
        game.getName().value(),
        game.getReleaseDate().value(),
        game.getPrice().value());
  }

  public Game toGame() {
    return new Game(id, name, releaseDate, price);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
