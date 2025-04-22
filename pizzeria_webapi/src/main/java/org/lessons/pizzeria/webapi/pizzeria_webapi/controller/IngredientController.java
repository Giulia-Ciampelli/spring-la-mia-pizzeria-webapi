package org.lessons.pizzeria.webapi.pizzeria_webapi.controller;


import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Ingredient;
import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Pizza;
import org.lessons.pizzeria.webapi.pizzeria_webapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    
    @Autowired
    private IngredientRepository ingredientRepository;

    // sezione index
    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "ingredients/index";
    }

    // sezione show
    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        return "ingredients/show";
    }

    // sezione create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }

    // sezione create
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "ingredients/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }

    // sezione delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Ingredient ingredientToDelete = ingredientRepository.findById(id).get();

        for (Pizza linkedPizza : ingredientToDelete.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredientToDelete);
        }

        ingredientRepository.delete(ingredientToDelete);
        return "redirect:/ingredients";
    }
}
