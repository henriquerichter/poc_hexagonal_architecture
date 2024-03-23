package com.test.domain.game;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface GameSaver {
    Game saveGame(String name, LocalDate releaseDate, BigDecimal price);
}
