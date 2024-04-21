package org.remitly;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class JSONParser {

    private static final Logger logger = LoggerFactory.getLogger(JSONParser.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public boolean validateFromPath(String path) {
        try (FileReader reader = new FileReader(path)) {
            JsonNode node = mapper.readTree(reader);
            boolean isSchemaCorrect = checkSchema(node);
            boolean containsStarOnly = checkIfContainsStarOnly(node);

            return !containsStarOnly;

        } catch (IOException e) {
            logger.error(e.getMessage());
            return true;
        }
    }

    public boolean validateFromString(String json) {
        try {
            JsonNode node = mapper.readTree(json);
            boolean isSchemaCorrect = checkSchema(node);
            boolean containsStarOnly = checkIfContainsStarOnly(node);

            return !containsStarOnly;

        } catch (IOException e) {
            logger.error(e.getMessage());
            return true;
        }
    }

    public boolean checkSchema(JsonNode root) {
        try (FileInputStream reader = new FileInputStream("src/main/resources/JSONSchema.json")) {

            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema jsonSchema = factory.getSchema(reader);
            Set<ValidationMessage> errors = jsonSchema.validate(root);

            if (!errors.isEmpty()) {
                logger.info("Warning! JSON Schema is incorrect");
            }

            return errors.isEmpty();

        } catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean checkIfContainsStarOnly(JsonNode root) {
        JsonNode nested;

        if (root.has("PolicyDocument")) {
            nested = root.get("PolicyDocument");

            if (nested.has("Statement")) {
                nested = nested.get("Statement");

                for (JsonNode statementElement : nested) {

                    if (statementElement.has("Resource")) {
                        JsonNode resource = statementElement.get("Resource");

                        if (resource.asText().equals("*")) {
                            return true;
                        }

                        for (JsonNode resourceElement : resource) {
                            if (resourceElement.asText().equals("*")) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
