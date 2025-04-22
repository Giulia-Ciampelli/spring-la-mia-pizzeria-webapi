package org.lessons.pizzeria.webapi.pizzeria_webapi.service;

import java.util.List;
import java.util.Optional;

import org.lessons.pizzeria.webapi.pizzeria_webapi.exception.IdNotFoundException;
import org.lessons.pizzeria.webapi.pizzeria_webapi.model.OnSale;
import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Pizza;
import org.lessons.pizzeria.webapi.pizzeria_webapi.repository.OnSaleRepository;
import org.lessons.pizzeria.webapi.pizzeria_webapi.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OnSaleRepository saleRepository;

    // index
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    // show
    public Pizza getById(int id) {
        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);

        if (pizzaAttempt.isEmpty()) {
            throw new IdNotFoundException("404: Pizza with id " + id + " not found.");
        }

        return pizzaAttempt.get();
    }

    // ricerche personalizzate
    public List<Pizza> findByName(String name) {
        return pizzaRepository.findByNameContainingIgnoreCase(name);
    }

    // create
    public Pizza create(Pizza formPizza) {
        return pizzaRepository.save(formPizza);
    }

    // update
    public Pizza update(Pizza formPizza) {
        return pizzaRepository.save(formPizza);
    }

    // delete
    public void deleteById(int id) {

        Pizza pizza = getById(id);

        for (OnSale saleToDelete : pizza.getSales()) {
            saleRepository.delete(saleToDelete);
        }

        pizzaRepository.delete(pizza);
    }

    public void delete(Pizza pizza) {
        for (OnSale saleToDelete : pizza.getSales()) {
            saleRepository.delete(saleToDelete);
        }

        pizzaRepository.delete(pizza);
    }
}
