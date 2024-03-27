package org.example.validationOption;

import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;

public class CourtValidationOptions {
    public static ValidationOptions options = new ValidationOptions().validator(
            Document.parse("    {\n" +
                           "      $jsonSchema: {\n" +
                           "         bsonType: \"object\"\n" +
                           "         properties: {\n" +
                           "            is_rented: {\n" +
                           "               bsonType: \"int\",\n" +
                           "               minimum: 0,\n" +
                           "               maximum: 1,\n" +
                           "               description: \"is_rented can have only value of 0 or 1\"\n" +
                           "            },\n" +
                           "         }\n" +
                           "      }\n" +
                           "    }                            \n")
    );
}
