package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    public void beforeEach() {
        Validator validator = new Validator();
        this.schema = validator.number();
    }

    @Test
    public void complexValidationTest() {
        assertThat(schema.isValid(null)).isTrue();
        schema.required().positive().range(2, 7);
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(-5)).isFalse();
        assertThat(schema.isValid(8)).isFalse();
    }

    @Test
    public void requiredTest() {
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("5")).isFalse();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(-111)).isTrue();
    }

    @Test
    public void positiveTest() {
        schema.positive();
        assertThat(schema.isValid(-17)).isFalse();
        assertThat(schema.isValid(44)).isTrue();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void rangeTest() {
        schema.range(-38, 21);
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(-38)).isTrue();
        assertThat(schema.isValid(21)).isTrue();
        assertThat(schema.isValid(25)).isFalse();
        assertThat(schema.isValid(null)).isTrue();
    }
}
