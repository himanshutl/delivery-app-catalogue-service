package com.deliveryapp.catalogueservice.repository;

import com.deliveryapp.catalogueservice.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogueRepository extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findByRestaurantId(long restaurantId);
}
