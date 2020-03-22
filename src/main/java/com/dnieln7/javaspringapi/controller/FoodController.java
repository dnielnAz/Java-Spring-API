package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.controller.response.DeleteResponse;
import com.dnieln7.javaspringapi.data.model.Food;
import com.dnieln7.javaspringapi.data.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("/food")
    public List<Food> getFood(@RequestParam(required = false) Integer store) {
        List<Food> food;

        if(store == null) {
            food = new ArrayList<>();
            foodRepository.findAll().forEach(food::add);
        }
        else {
            food = foodRepository.findByStoreId(store);
        }

        return food;
    }

    @GetMapping("/food/{id}")
    public Food getFoodById(@PathVariable int id) {
        return foodRepository.findById(id).orElse(null);
    }

    @PostMapping("/food")
    public Food postFood(@RequestBody Food food) {
        return foodRepository.save(food);
    }

    @PutMapping("/food/{id}")
    public Food putFood(@PathVariable int id, @RequestBody Food food) {
        food.setId(id);

        return foodRepository.save(food);
    }

    @DeleteMapping("/food/{id}")
    public DeleteResponse deleteFood(@PathVariable int id) {
        Food food = foodRepository.findById(id).orElse(null);

        if (food == null) {
            return new DeleteResponse(1, "Not found!");
        }

        foodRepository.delete(food);

        return new DeleteResponse(1, "Deleted!");
    }
}
