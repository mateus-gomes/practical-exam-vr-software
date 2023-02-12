package br.com.mateusg.practicalexam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {
    @Id
    private Long idProduct;

    @Size(min = 1, max = 150, message = "Description must have between 1 and 150 characters")
    @NotNull(message = "Description is a required field and should not be empty")
    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Positive(message = "Price should be a positive number")
    @NotNull(message = "Price is a required field and should not be empty")
    @Column(name = "price", nullable = false, scale = 2)
    private Double price;

    public Product() {
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = convertToTwoDecimalPlaces(price);
    }

    private Double convertToTwoDecimalPlaces(Double number){
        return Math.round(number * 100.0) / 100.0;
    }
}
