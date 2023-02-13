package br.com.mateusg.practicalexam.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client_order")
public class Order {

    @Id
    private Long idOrder;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "order_status", nullable = false)
    private String status;

    @JsonManagedReference
    @OneToMany(mappedBy = "orders")
    private List<OrderClientProduct> orderItems = new ArrayList<>();

    public Order() {
        this.orderDate = LocalDate.now();
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderClientProduct> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderClientProduct> orderItems) {
        this.orderItems = orderItems;
    }
}
