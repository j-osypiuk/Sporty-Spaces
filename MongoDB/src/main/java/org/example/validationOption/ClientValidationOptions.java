package org.example.validationOption;

import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;

public class ClientValidationOptions {

    public static ValidationOptions options = new ValidationOptions().validator(
            Document.parse("""
                        {
                          $jsonSchema: {
                             bsonType: "object"
                             properties: {
                                has_rent: {
                                   bsonType: "int",
                                   minimum: 0,
                                   maximum: 1,
                                   description: "has_rent can have only value of 0 or 1"
                                },
                             }
                          }
                        }                           \s
                    """)
    );
}
