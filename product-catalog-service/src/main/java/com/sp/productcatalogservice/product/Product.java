package com.sp.productcatalogservice.product;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Builder
public class Product {

    @Id
    private String id;

    private String productName;

    private String description;

    private String[] images;

    private float price;

    private int quantity;
}
