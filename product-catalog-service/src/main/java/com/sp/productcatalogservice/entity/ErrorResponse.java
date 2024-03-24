package com.sp.productcatalogservice.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @Builder @AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
}
