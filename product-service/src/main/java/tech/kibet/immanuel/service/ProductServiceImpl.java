package tech.kibet.immanuel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.kibet.immanuel.dto.ProductResponse;
import tech.kibet.immanuel.exception.ItemNotFoundException;
import tech.kibet.immanuel.model.Product;
import tech.kibet.immanuel.repository.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public ProductResponse createProduct(Product request) {
        Product newProduct = productRepo.save(request);
        log.info("New Product {}", newProduct);
        return ProductResponse.builder()
                .id(newProduct.getId())
                .name(newProduct.getName())
                .description(newProduct.getDescription())
                .price(newProduct.getPrice())
                .quantity(newProduct.getQuantity())
                .build();
    }


    @Override
    public ProductResponse updateProduct(String id, Product request) {
        Product product = findProductById(id);
        product.setName(request.getName() != null ? request.getName() : product.getName());
        product.setDescription(request.getDescription() !=null ? request.getDescription() : product.getDescription());
        product.setPrice(request.getPrice() !=null ? request.getPrice() : product.getPrice());
        product.setQuantity(request.getQuantity() !=null ? request.getQuantity() : product.getQuantity());

        productRepo.save(product);
        return mapToProductResponse(product);
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = findProductById(id);
        log.info("Product {} found", product.getId());
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Override
    public void deleteProduct(String id) {
        Product product = findProductById(id);
        productRepo.delete(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        return allProducts.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    private Product findProductById(String id){
        return productRepo.findById(id).orElseThrow(() -> new ItemNotFoundException("Product with id " + id + " not found"));
    }
}
