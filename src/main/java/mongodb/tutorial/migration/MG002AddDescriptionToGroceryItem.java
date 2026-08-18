package mongodb.tutorial.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;

@ChangeUnit(id = "add-description-field-to-grocery-item", order = "002", author = "developer")
public class MG002AddDescriptionToGroceryItem {

    @Execution
    public void execute(MongoTemplate mongoTemplate) {
        mongoTemplate.getCollection("GroceryItem").updateMany(
                new Document("description", new Document("$exists", false)),
                new Document("$set", new Document("description", "No description provided"))
        );

        MongoJsonSchema newSchema = MongoJsonSchema.builder()
                .required("name", "price", "description")
                .properties(
                        JsonSchemaProperty.string("name"),
                        JsonSchemaProperty.int32("quantity"),
                        JsonSchemaProperty.decimal128("category"),
                        JsonSchemaProperty.string("description")
                ).build();

        Document command = new Document("collMod", "GroceryItem")
                .append("validator", Validator.schema(newSchema).toDocument())
                .append("validationLevel", "strict");

        mongoTemplate.executeCommand(command);
    }

    @RollbackExecution
    public void rollbackExecute(MongoTemplate mongoTemplate) {
        MongoJsonSchema oldSchema = MongoJsonSchema.builder()
                .required("name", "price")
                .properties(
                        JsonSchemaProperty.string("name"),
                        JsonSchemaProperty.int32("quantity"),
                        JsonSchemaProperty.decimal128("price")
                ).build();
        Document command = new Document("collMod", "GroceryItem")
                .append("validator", Validator.schema(oldSchema).toDocument());
        mongoTemplate.executeCommand(command);
    }
}
