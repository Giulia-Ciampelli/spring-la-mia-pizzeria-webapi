package org.lessons.pizzeria.webapi.pizzeria_webapi.controller;

import java.util.List;

import org.lessons.pizzeria.webapi.pizzeria_webapi.model.OnSale;
import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Pizza;
import org.lessons.pizzeria.webapi.pizzeria_webapi.repository.IngredientRepository;
import org.lessons.pizzeria.webapi.pizzeria_webapi.repository.OnSaleRepository;
import org.lessons.pizzeria.webapi.pizzeria_webapi.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OnSaleRepository saleRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Pizza pizza = pizzaRepository.findById(id).get();
        model.addAttribute("pizza", pizza);
        model.addAttribute("sales", pizza.getSales());
        return "pizzas/show";
    }

    // #region ricerche personalizzate
    @GetMapping("/search-by-name")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Pizza> pizzas = pizzaRepository.findByNameContainingIgnoreCase(name);
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    // #endregion ricerche personalizzate

    // create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        // controllo errori
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizzas/create-edit";
        }

        // salvataggio con la repository
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas";
    }

    // update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("edit", true);
        return "pizzas/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult, Model model) {

        // controllo errori
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizzas/create-edit";
        }

        // salvataggio con la repository
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas";
    }

    // delete
    @PostMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        Pizza pizza = pizzaRepository.findById(id).get();

        for (OnSale saleToDelete : pizza.getSales()) {
            saleRepository.delete(saleToDelete);
        }

        pizzaRepository.delete(pizza);
        return "redirect:/pizzas";
    }

    // metodo delle offerte
    @GetMapping("/{id}/sale")
    public String onSale(@PathVariable int id, Model model) {
        OnSale sale = new OnSale();
        sale.setPizza(pizzaRepository.findById(id).get());
        model.addAttribute("sale", sale);
        return "sales/create-edit";
    }

}
