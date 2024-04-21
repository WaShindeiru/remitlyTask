package org.remitly;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JSONParserTest {

    private final JSONParser parser = new JSONParser();

    @Test
    void validateInvalid() {
        String path = "src/test/resources/invalid.json";
        boolean isCorrect = parser.validateFromPath(path);
        assertThat(isCorrect).isFalse();
    }

    @Test
    void validateValidMultipleResources() {
        String path = "src/test/resources/valid_multiple_resources.json";
        boolean isCorrect = parser.validateFromPath(path);
        assertThat(isCorrect).isTrue();
    }

    @Test
    void validateInvalidMultipleResources() {
        String path = "src/test/resources/invalid_multiple_resources.json";
        boolean isCorrect = parser.validateFromPath(path);
        assertThat(isCorrect).isFalse();
    }

    @Test
    void validateValidMultipleStatementsOneResource() {
        String path = "src/test/resources/valid_multiple_statements_one_resource.json";
        boolean isCorrect = parser.validateFromPath(path);
        assertThat(isCorrect).isTrue();
    }

    @Test
    void validateInvalidMultipleStatementsOneResource() {
        String path = "src/test/resources/invalid_multiple_statements_one_resource.json";
        boolean isCorrect = parser.validateFromPath(path);
        assertThat(isCorrect).isFalse();
    }

    @Test
    void validateEmpty() {
        String path = "src/test/resources/empty.json";
        boolean isCorrect = parser.validateFromPath(path);
        assertThat(isCorrect).isTrue();
    }

    @Test
    void validateWrongSchemaRedundantLocationField() {
        String path = "src/test/resources/wrong_schema_redundant_location_field.json";
        boolean isCorrect = parser.validateFromPath(path);
        assertThat(isCorrect).isTrue();
    }

    @Test
    void validateNoResource() {
        String path = "src/test/resources/no_resource.json";
        boolean isCorrect = parser.validateFromPath(path);
        assertThat(isCorrect).isTrue();
    }
}