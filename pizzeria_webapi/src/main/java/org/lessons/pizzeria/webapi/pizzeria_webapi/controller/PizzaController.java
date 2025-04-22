package org.lessons.pizzeria.webapi.pizzeria_webapi.controller;

import java.util.List;

import org.lessons.pizzeria.webapi.pizzeria_webapi.model.OnSale;
import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Pizza;
import org.lessons.pizzeria.webapi.pizzeria_webapi.service.IngredientService;
import org.lessons.pizzeria.webapi.pizzeria_webapi.service.OnSaleService;
import org.lessons.pizzeria.webapi.pizzeria_webapi.service.PizzaService;
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
    private PizzaService pizzaService;

    @Autowired
    private OnSaleService saleService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzas = pizzaService.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Pizza pizza = pizzaService.getById(id);
        model.addAttribute("pizza", pizza);
        model.addAttribute("sales", pizza.getSales());
        return "pizzas/show";
    }

    // #region ricerche personalizzate
    @GetMapping("/search-by-name")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Pizza> pizzas = pizzaService.findByName(name);
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    // #endregion ricerche personalizzate

    // create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientService.findAll());
        return "pizzas/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        // controllo errori
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "pizzas/create-edit";
        }

        // salvataggio con la repository
        pizzaService.create(formPizza);
        return "redirect:/pizzas";
    }

    // update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", ingredientService.findAll());
        model.addAttribute("edit", true);
        return "pizzas/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult, Model model) {

        // controllo errori
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "pizzas/create-edit";
        }

        // salvataggio con la repository
        pizzaService.update(formPizza);
        return "redirect:/pizzas";
    }

    // delete
    @PostMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        Pizza pizza = pizzaService.getById(id);

        for (OnSale saleToDelete : pizza.getSales()) {
            saleService.delete(saleToDelete);
        }

        pizzaService.delete(pizza);
        return "redirect:/pizzas";
    }

    // metodo delle offerte
    @GetMapping("/{id}/sale")
    public String onSale(@PathVariable int id, Model model) {
        OnSale sale = new OnSale();
        sale.setPizza(pizzaService.getById(id));
        model.addAttribute("sale", sale);
        return "sales/create-edit";
    }
}
