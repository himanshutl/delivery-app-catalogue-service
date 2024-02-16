package com.deliveryapp.catalogueservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_generator", sequenceName = "item_seq", allocationSize = 1)
    private long id;

    @Column(nullable = false)
    private String itemName;

    private String itemDescription;

    @Column(nullable = false)
    private String itemImage;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private boolean isVeg;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int quantity;
}
