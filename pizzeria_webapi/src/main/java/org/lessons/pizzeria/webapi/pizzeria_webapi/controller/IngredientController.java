package org.lessons.pizzeria.webapi.pizzeria_webapi.controller;


import java.util.List;

import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Ingredient;
import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Pizza;
import org.lessons.pizzeria.webapi.pizzeria_webapi.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    
    @Autowired
    private IngredientService ingredientService;

    // sezione index
    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients = ingredientService.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    // sezione show
    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("ingredient", ingredientService.getById(id));
        return "ingredients/show";
    }
    
    // #region ricerche personalizzate
    @GetMapping("/search-by-name")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Ingredient> ingredients = ingredientService.findByName(name);
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    // #endregion ricerche personalizzate

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
        ingredientService.create(formIngredient);
        return "redirect:/ingredients";
    }

    // sezione edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("ingredient", ingredientService.getById(id));
        model.addAttribute("edit", true);
        return "ingredients/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }
        ingredientService.update(formIngredient);
        return "redirect:/ingredients";
    }

    // sezione delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Ingredient ingredientToDelete = ingredientService.getById(id);

        for (Pizza linkedPizza : ingredientToDelete.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredientToDelete);
        }

        ingredientService.delete(ingredientToDelete);
        return "redirect:/ingredients";
    }
}
