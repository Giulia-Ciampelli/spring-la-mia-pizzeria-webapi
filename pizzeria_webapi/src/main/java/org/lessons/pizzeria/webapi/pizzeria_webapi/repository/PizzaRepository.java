package org.lessons.pizzeria.webapi.pizzeria_webapi.repository;

import java.util.List;

import org.lessons.pizzeria.webapi.pizzeria_webapi.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    
    // ricerca per nome, containing e ignore case
    public List<Pizza> findByNameContainingIgnoreCase(String name);
}
