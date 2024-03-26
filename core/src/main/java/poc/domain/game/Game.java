package poc.domain.game;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Game {

    private GameId id;
    private Name name;
    private ReleaseDate releaseDate;
    private Price price;

    public Game(Long id, String name, LocalDate releaseDate, BigDecimal price) {
        this.id = new GameId(id);
        this.name = new Name(name);
        this.releaseDate = new ReleaseDate(releaseDate);
        this.price = new Price(price);
    }

    public Game(String name, LocalDate releaseDate, BigDecimal price) {
        this.name = new Name(name);
        this.releaseDate = new ReleaseDate(releaseDate);
        this.price = new Price(price);
    }

    public GameId getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ReleaseDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String toJson() {
        return new ObjectMapper().createObjectNode()
                .put("id", id.value())
                .put("name", name.value())
                .put("releaseDate", releaseDate.value().toString())
                .put("price", price.value().toString())
                .toString();
    }
}
