package mongodb.tutorial.repository;

import mongodb.tutorial.model.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroceryItemRepository extends MongoRepository<GroceryItem, String> {
}
