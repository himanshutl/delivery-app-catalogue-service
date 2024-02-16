package com.deliveryapp.catalogueservice.controller;

import com.deliveryapp.catalogueservice.dto.FoodCatalogue;
import com.deliveryapp.catalogueservice.dto.FoodItemDto;
import com.deliveryapp.catalogueservice.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin(origins = "*")
public class CatalogueController {
    @Autowired
    private CatalogueService catalogueService;

    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDto> addFoodItem(@RequestBody FoodItemDto foodItemDto) {
        FoodItemDto foodItemSaved = catalogueService.addFoodItem(foodItemDto);
        return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCatalogue> fetchRestaurantDetailsWithFoodMenu(@PathVariable long restaurantId){
        FoodCatalogue foodCatalogue = catalogueService.fetchFoodCatalogueDetails(restaurantId);
        return new ResponseEntity<>(foodCatalogue, HttpStatus.OK);
    }

}
