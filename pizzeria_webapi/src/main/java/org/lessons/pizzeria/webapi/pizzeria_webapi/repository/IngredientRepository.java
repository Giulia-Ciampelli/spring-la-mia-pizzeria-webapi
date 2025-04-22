package org.lessons.pizzeria.webapi.pizzeria_webapi.repository;

import java.util.List;

import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
        
    // ricerca per nome, containing e ignore case
    public List<Ingredient> findByNameContainingIgnoreCase(String name);
}
