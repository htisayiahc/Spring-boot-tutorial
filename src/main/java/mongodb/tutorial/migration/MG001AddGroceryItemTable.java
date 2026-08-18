package mongodb.tutorial.migration;

import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;

@ChangeUnit(id= "add-grocery-item-table", order = "001", author = "FHX")
public class MG001AddGroceryItemTable {
    @Execution
    public void execution(MongoTemplate mongoTemplate) {

        try {
            MongoJsonSchema schema = MongoJsonSchema.builder()
                    .required("name", "quantity") // กำหนดว่าต้องมี field เหล่านี้
                    .properties(
                            JsonSchemaProperty.string("name"),
                            JsonSchemaProperty.int32("quantity"),
                            JsonSchemaProperty.decimal128("category")
                    )
                    .build();

            CollectionOptions options = CollectionOptions.empty()
                    .validator(Validator.schema(schema));

            // สร้าง Collection พร้อม Schema Validation
            if (!mongoTemplate.collectionExists("GroceryItem")) {
                mongoTemplate.createCollection("GroceryItem", options);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        if (mongoTemplate.collectionExists("GroceryItem")) {
            mongoTemplate.dropCollection("GroceryItem");
        }
    }
}

