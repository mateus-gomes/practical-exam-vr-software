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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
