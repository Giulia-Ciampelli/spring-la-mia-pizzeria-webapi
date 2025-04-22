package org.lessons.pizzeria.webapi.pizzeria_webapi.repository;

import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
    
}
