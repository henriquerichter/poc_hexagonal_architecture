package com.test.domain.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class PriceTest {

  @Test
  void givenPrice_whenInstantiated_thenGettersReturnCorrectValues() {
    // given
    BigDecimal price = new BigDecimal("10.00");

    // when
    Price actualPrice = new Price(price);

    // then
    assertEquals(price, actualPrice.value());
  }

  @Test
  void givenNegativePrice_whenInstantiated_thenThrowIllegalArgumentException() {
    // given
    BigDecimal price = new BigDecimal("-10.00");

    // when
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Price(price));

    // then
    assertEquals("Price cannot be null or negative", exception.getMessage());
  }

  @Test
  void givenNullPrice_whenInstantiated_thenThrowIllegalArgumentException() {
    // given
    BigDecimal price = null;

    // when
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Price(price));

    // then
    assertEquals("Price cannot be null or negative", exception.getMessage());
  }
}
