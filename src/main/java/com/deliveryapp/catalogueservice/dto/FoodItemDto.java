package com.deliveryapp.catalogueservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDto {
    private long id;
    private String itemName;
    private String itemDescription;
    private String itemImage;
    private BigDecimal price;
    private Long restaurantId;
    private boolean isVeg;
    private int quantity;
}
