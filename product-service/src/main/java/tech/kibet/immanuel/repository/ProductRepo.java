package tech.kibet.immanuel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.kibet.immanuel.model.Product;

public interface ProductRepo extends MongoRepository<Product, String> {
}
