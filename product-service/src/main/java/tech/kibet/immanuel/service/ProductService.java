package tech.kibet.immanuel.service;

import tech.kibet.immanuel.dto.ProductResponse;
import tech.kibet.immanuel.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    ProductResponse createProduct(Product request);
    ProductResponse updateProduct(String id, Product request);
    ProductResponse getProductById(String id);
    void deleteProduct(String id);



}
