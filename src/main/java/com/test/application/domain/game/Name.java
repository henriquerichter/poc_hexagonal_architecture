package com.test.application.domain.game;

public record Name(String value) {

  public Name(String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    this.value = value;
  }
}
