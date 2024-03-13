package com.sp.ordercreationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Order {
    private String id;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private Address shippingAddress;
    private Address billingAddress;

}
