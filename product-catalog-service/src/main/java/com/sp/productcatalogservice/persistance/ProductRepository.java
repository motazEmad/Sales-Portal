package com.sp.productcatalogservice.persistance;

import com.sp.productcatalogservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByProductName(String productName);
}
