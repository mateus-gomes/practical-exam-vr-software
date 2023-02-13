package br.com.mateusg.practicalexam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "order_client_product")
public class OrderClientProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_client_product_id")
    private Long orderClientProductId;

    @Column(name = "product_amount", nullable = false)
    @Positive(message = "Product amount should be a positive number")
    private int productAmount;

    @ManyToOne
    @JoinColumn(name = "fk_client",  nullable = false)
    @NotNull(message = "An order item should have a client associated")
    private Client clients;

    @ManyToOne
    @JoinColumn(name = "fk_product",  nullable = false)
    @NotNull(message = "An order item should have a product associated")
    private Product products;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_order",  nullable = false)
    @NotNull(message = "An order item should have a order associated")
    private Order orders;

    public OrderClientProduct() {
    }

    public Long getOrderClientProductId() {
        return orderClientProductId;
    }

    public void setOrderClientProductId(Long orderClientProductId) {
        this.orderClientProductId = orderClientProductId;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public Client getClients() {
        return clients;
    }

    public void setClients(Client clients) {
        this.clients = clients;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }
}
