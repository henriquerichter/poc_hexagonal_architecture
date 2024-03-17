package com.test.domain.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ReleaseDateTest {

  @Test
  void givenReleaseDate_whenInstantiated_thenGettersReturnCorrectValues() {
    // given
    LocalDate releaseDate = LocalDate.of(2020, 1, 1);

    // when
    ReleaseDate actualReleaseDate = new ReleaseDate(releaseDate);

    // then
    assertEquals(releaseDate, actualReleaseDate.value());
  }

  @Test
  void givenNullReleaseDate_whenInstantiated_thenThrowIllegalArgumentException() {
    // given
    LocalDate releaseDate = null;

    // when
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ReleaseDate(releaseDate));

    // then
    assertEquals("Release date cannot be null", exception.getMessage());
  }
}
