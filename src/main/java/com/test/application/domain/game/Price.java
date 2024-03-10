package com.test.application.domain.game;

import java.math.BigDecimal;

public record Price(BigDecimal value) {

  public Price(BigDecimal value) {
    if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Price cannot be null or negative");
    }
    this.value = value;
  }
}
