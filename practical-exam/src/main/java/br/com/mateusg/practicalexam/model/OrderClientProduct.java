package br.com.mateusg.practicalexam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_client_product")
public class OrderClientProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_client_product_id")
    private Long orderClientProductId;

    @Column(name = "product_amount", nullable = false)
    private int productAmount;

    @ManyToOne
    @JoinColumn(name = "client_fk")
    private Client clients;

    @ManyToOne
    @JoinColumn(name = "product_fk")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_fk")
    private Order order;
}
