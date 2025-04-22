package org.lessons.pizzeria.webapi.pizzeria_webapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sales")
public class OnSale {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title of sale cannot be empty.")
    private String title;

    @NotNull(message = "Sale start date cannot be empty.")
    @FutureOrPresent(message = "Sale start date cannot be in the past.")
    private LocalDateTime startDate;
    
    @NotNull(message = "Sale finish date cannot be empty.")
    @FutureOrPresent(message = "Sale finish date cannot be in the past.")
    private LocalDateTime finishDate;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    // #region gettere e setter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    
    // #endregion gettere e setter
}
