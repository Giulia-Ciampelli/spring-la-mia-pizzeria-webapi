package org.lessons.pizzeria.webapi.pizzeria_webapi.service;

import java.util.List;
import java.util.Optional;

import org.lessons.pizzeria.webapi.pizzeria_webapi.exception.IdNotFoundException;
import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Ingredient;
import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Pizza;
import org.lessons.pizzeria.webapi.pizzeria_webapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientRepository ingredientRepository;

    // index
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    // show
    public Ingredient getById(int id) {
        Optional<Ingredient> ingredientAttempt = ingredientRepository.findById(id);

        if (ingredientAttempt.isEmpty()) {
            throw new IdNotFoundException("404: Ingredient with id " + id + " not found.");
        }

        return ingredientAttempt.get();
    }

    // ricerche personalizzate
    public List<Ingredient> findByName(String name) {
        return ingredientRepository.findByNameContainingIgnoreCase(name);
    }

    // create
    public Ingredient create(Ingredient formIngredient) {
        return ingredientRepository.save(formIngredient);
    }

    // update
    public Ingredient update(Ingredient formIngredient) {
        return ingredientRepository.save(formIngredient);
    }

    // delete
        public void deleteById(int id) {

        Ingredient ingredient = getById(id);

        for (Pizza linkedPizza : ingredient.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredient);
        }

        ingredientRepository.delete(ingredient);
    }

    public void delete(Ingredient ingredient) {
        for (Pizza linkedPizza : ingredient.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredient);
        }

        ingredientRepository.delete(ingredient);
    }
}
