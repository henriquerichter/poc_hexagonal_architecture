package com.test.domain.game;

import java.time.LocalDate;

public record ReleaseDate(LocalDate value) {

    public ReleaseDate {
        if (value == null) {
            throw new IllegalArgumentException("Release date cannot be null");
        }
    }
}
