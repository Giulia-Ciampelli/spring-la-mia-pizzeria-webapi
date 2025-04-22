package org.lessons.pizzeria.webapi.pizzeria_webapi.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Pizza;
import org.lessons.pizzeria.webapi.pizzeria_webapi.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Pizza> show(@PathVariable int id) {
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);

        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404));
        }

        return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatusCode.valueOf(200));
    }

    // create
    @PostMapping
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(pizzaService.create(pizza), HttpStatusCode.valueOf(201));
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable int id, @RequestBody Pizza pizza) {
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);

        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404));
        }

        pizza.setId(id);
        return new ResponseEntity<Pizza>(pizzaService.update(pizza), HttpStatusCode.valueOf(201));
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable int id) {
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);

        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404));
        }

        pizzaService.deleteById(id);
        return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(204));
    }
}
