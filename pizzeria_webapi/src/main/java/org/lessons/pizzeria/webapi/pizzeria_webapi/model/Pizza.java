package org.lessons.pizzeria.webapi.pizzeria_webapi.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "this field must contain at least one non-whitespace character")
    @Size(min = 3, max = 255, message = "the name must be between 3 and 255 characters")
    private String name;

    @NotBlank(message = "this field must contain at least one non-whitespace character")
    @Size(min = 10, max = 255, message = "the description must be between 10 and 255 characters")
    private String description;

    @NotBlank(message = "this field must contain at least one non-whitespace character")
    @Lob
    private String url;

    @NotNull(message = "this field cannot be empty")
    @Min(value = 0, message = "price cannot be negative")
    private float price;

    // RICORDA: metti fetchType eager prima per evitare di sclerare di nuovo
    @OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
    private List<OnSale> sales;

    @ManyToMany
    @JoinTable(name = "ingredient_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    //#region getter e setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public float getPrice() {
        return price;
    }

    public List<OnSale> getSales() {
        return sales;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSales(List<OnSale> sales) {
        this.sales = sales;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    //#endregion getter e setter

    @Override
    public String toString() {
        return String.format("%s, %s, %.2f", this.name, this.description, this.price);
    }
}
