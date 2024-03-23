package com.test.domain.game;

public record GameId(Long value) {

    public GameId {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("Game ID cannot be null or negative");
        }
    }
}
