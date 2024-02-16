package com.deliveryapp.catalogueservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCatalogue {
    private List<FoodItemDto> foodItemsDtoList;
    private RestaurantDto restaurantDto;
}
