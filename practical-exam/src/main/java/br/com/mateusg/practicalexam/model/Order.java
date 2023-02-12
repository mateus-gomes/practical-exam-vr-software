package br.com.mateusg.practicalexam.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "client_order")
public class Order {

    @Id
    private Long idOrder;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "order_status", nullable = false)
    private String status;

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
}
