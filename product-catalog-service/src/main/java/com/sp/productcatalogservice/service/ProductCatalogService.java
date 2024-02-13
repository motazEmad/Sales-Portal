package com.sp.productcatalogservice.service;

import com.sp.productcatalogservice.exception.BusinessException;
import com.sp.productcatalogservice.persistance.ProductRepository;
import com.sp.productcatalogservice.product.ErrorResponse;
import com.sp.productcatalogservice.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product/v1")
public class ProductCatalogService {

    @Autowired
    ProductRepository productRepository;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> mapException(Exception e) {
        return ResponseEntity.badRequest().body(ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(e.getMessage()).build());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createProduct(@RequestBody Product product) throws URISyntaxException, BusinessException {
        boolean productFound = productRepository.findByProductName(product.getProductName())
                .isPresent();
        if(productFound) {
            throw new BusinessException("Product Name already exists");
        }
        Product saved = productRepository.save(product);
        return ResponseEntity.created(new URI("/product/" + saved.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable String id) {
        Optional<Product> product = productRepository.findById(id);
        return !product.isPresent() ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(product.get());
    }

    // TODO mask quantity for not admin role
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }
}
