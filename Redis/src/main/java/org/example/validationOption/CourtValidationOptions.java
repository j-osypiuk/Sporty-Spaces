package org.example.validationOption;

import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;

public class CourtValidationOptions {
    public static ValidationOptions options = new ValidationOptions().validator(
            Document.parse("""
                        {
                          $jsonSchema: {
                             bsonType: "object"
                             properties: {
                                is_rented: {
                                   bsonType: "int",
                                   minimum: 0,
                                   maximum: 1,
                                   description: "is_rented can have only value of 0 or 1"
                                },
                             }
                          }
                        }                           \s
                    """)
    );
}
