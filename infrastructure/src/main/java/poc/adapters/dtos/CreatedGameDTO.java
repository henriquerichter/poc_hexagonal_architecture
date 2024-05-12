package poc.adapters.dtos;

import poc.adapters.json.To;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatedGameDTO(Long id, String name, LocalDate releaseDate, BigDecimal price) {

    @Override
    public String toString() {
        return To.json(this);
    }
}
