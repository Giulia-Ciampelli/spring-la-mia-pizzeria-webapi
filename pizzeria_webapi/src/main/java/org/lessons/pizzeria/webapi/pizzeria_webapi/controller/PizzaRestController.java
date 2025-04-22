package org.lessons.pizzeria.webapi.pizzeria_webapi.controller;

import java.util.List;

import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Pizza;
import org.lessons.pizzeria.webapi.pizzeria_webapi.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {
    
    @Autowired
    private PizzaService pizzaService;

    // index
    @GetMapping
    public List<Pizza> index() {
        List<Pizza> pizzas = pizzaService.findAll();
        return pizzas;
    }

    // show
    @GetMapping("/{id}")
    public Pizza show(@PathVariable int id) {
        Pizza pizza = pizzaService.getById(id);
        return pizza;
    }

    // create
    @PostMapping
    public Pizza store(@RequestBody Pizza pizza) {
        return pizzaService.create(pizza);
    }

    // update
    @PutMapping("/{id}")
    public Pizza update(@PathVariable int id, @RequestBody Pizza pizza) {
        pizza.setId(id);
        return pizzaService.update(pizza);
    }

    // delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        pizzaService.deleteById(id);
    }
}
