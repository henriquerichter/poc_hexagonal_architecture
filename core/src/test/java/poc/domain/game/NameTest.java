package poc.domain.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NameTest {

    @Test
    void givenName_whenInstantiated_thenGettersReturnCorrectValues() {
        // given
        String name = "name";

        // when
        Name actualName = new Name(name);

        // then
        assertEquals(name, actualName.value());
    }

    @Test
    void givenBlankName_whenInstantiated_thenThrowIllegalArgumentException() {
        // given
        String name = " ";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Name(name));

        // then
        assertEquals("Name cannot be null or blank", exception.getMessage());
    }

    @Test
    void givenNullName_whenInstantiated_thenThrowIllegalArgumentException() {
        // given
        String name = null;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Name(name));

        // then
        assertEquals("Name cannot be null or blank", exception.getMessage());
    }
}
