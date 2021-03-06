package com.pascal7.ingre_api_mono.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascal7.ingre_api_mono.custom.RecipeDto;
import com.pascal7.ingre_api_mono.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/api/admin/product/recipe")
    public RecipeDto postRecipe(@RequestPart String recipeDto, @Nullable @RequestPart("upload") MultipartFile multipartFile) throws IOException {
        RecipeDto recipe = objectMapper.readValue(recipeDto, RecipeDto.class);
        recipe.setDate(new Timestamp(System.currentTimeMillis()));
        return recipeService.createWithFile(recipe, multipartFile);
    }

    @GetMapping("/api/product/recipe")
    public List<RecipeDto> getAllRecipe(){
        return recipeService.getAll();
    }

    @GetMapping("/api/product/recipe/{id}")
    public RecipeDto getRecipe(@PathVariable String id){
        return recipeService.getById(id);
    }

    @GetMapping("/api/product/recipe/category")
    public List<RecipeDto> getRecipeByCategory(@RequestParam String name){
        return recipeService.recipeByCategory(name);
    }

    @PutMapping("/api/admin/product/recipe")
    public RecipeDto updateRecipe(@RequestPart String recipeDto, @Nullable @RequestPart("upload") MultipartFile multipartFile) throws IOException {
        RecipeDto recipe = objectMapper.readValue(recipeDto, RecipeDto.class);
        return recipeService.updateWithFile(recipe, multipartFile);
    }

    @DeleteMapping("/api/admin/product/recipe/{id}")
    public void deleteRecipe(@PathVariable String id){
        recipeService.delete(id);
    }
}
