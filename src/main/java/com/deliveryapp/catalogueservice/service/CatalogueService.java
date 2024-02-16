package com.deliveryapp.catalogueservice.service;

import com.deliveryapp.catalogueservice.dto.FoodCatalogue;
import com.deliveryapp.catalogueservice.dto.FoodItemDto;
import com.deliveryapp.catalogueservice.dto.RestaurantDto;
import com.deliveryapp.catalogueservice.entity.FoodItem;
import com.deliveryapp.catalogueservice.repository.CatalogueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogueService {

    private CatalogueRepository catalogueRepository;
    private ModelMapper modelMapper;
    private RestTemplate restTemplate;

    public CatalogueService(CatalogueRepository catalogueRepository, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.catalogueRepository = catalogueRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }

    public FoodItemDto addFoodItem(FoodItemDto foodItemDto) {
        FoodItem foodItem = modelMapper.map(foodItemDto, FoodItem.class);
        FoodItem foodItemSaved = catalogueRepository.save(foodItem);
        return modelMapper.map(foodItemSaved, FoodItemDto.class);
    }

    public FoodCatalogue fetchFoodCatalogueDetails(long restaurantId) {
        List<FoodItemDto> foodItemListDto  = fetchFoodItemsList(restaurantId);
        RestaurantDto restaurantDto = fetchRestaurantDetailsFromRestaurantService(restaurantId);
        return getFoodCatalogue(foodItemListDto, restaurantDto);
    }

    /*
    associated with fetchFoodCatalogueDetails
     */
    private FoodCatalogue getFoodCatalogue(List<FoodItemDto> foodItemList, RestaurantDto restaurantDto) {
        FoodCatalogue foodCatalogue = new FoodCatalogue();
        foodCatalogue.setRestaurantDto(restaurantDto);
        foodCatalogue.setFoodItemsDtoList(foodItemList);
        return foodCatalogue;
    }

    /*
    associated with fetchFoodCatalogueDetails
     */
    private RestaurantDto fetchRestaurantDetailsFromRestaurantService(long restaurantId) {
        RestaurantDto restaurantDto =
        restTemplate
                .getForObject(
                        "http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId,
                        RestaurantDto.class);
        return restaurantDto;
    }

    /*
    associated with fetchFoodCatalogueDetails
     */
    private List<FoodItemDto> fetchFoodItemsList(long restaurantId) {
        List<FoodItem> foodItemList = catalogueRepository.findByRestaurantId(restaurantId);
        List<FoodItemDto> foodItemDtoList = new ArrayList<>();
        foodItemList.forEach(
                foodItem -> {
                    foodItemDtoList.add(modelMapper.map(foodItem, FoodItemDto.class));
                }
        );
        return foodItemDtoList;
    }


}
