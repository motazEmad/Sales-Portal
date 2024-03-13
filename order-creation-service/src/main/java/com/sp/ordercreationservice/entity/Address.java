package com.sp.ordercreationservice.entity;

public record Address(String streetName, String zipCode,
        String houseNo,
        String state,
        String country) {

}
