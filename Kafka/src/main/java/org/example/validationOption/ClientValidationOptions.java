package org.example.validationOption;

import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;

public class ClientValidationOptions {

    public static ValidationOptions options = new ValidationOptions().validator(
            Document.parse("    {\n" +
                           "      $jsonSchema: {\n" +
                           "         bsonType: \"object\"\n" +
                           "         properties: {\n" +
                           "            has_rent: {\n" +
                           "               bsonType: \"int\",\n" +
                           "               minimum: 0,\n" +
                           "               maximum: 1,\n" +
                           "               description: \"has_rent can have only value of 0 or 1\"\n" +
                           "            },\n" +
                           "         }\n" +
                           "      }\n" +
                           "    }                            \n")
    );
}
