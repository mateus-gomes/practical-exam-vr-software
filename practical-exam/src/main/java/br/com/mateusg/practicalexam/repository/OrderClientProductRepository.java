package br.com.mateusg.practicalexam.repository;

import br.com.mateusg.practicalexam.model.OrderClientProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderClientProductRepository extends JpaRepository<OrderClientProduct, Long> {

    @Query(value = "SELECT * FROM order_client_product WHERE fk_product = ?1 and fk_order = ?2", nativeQuery = true)
    Optional<OrderClientProduct> findByProductAndOrder(Long idProduct, Long idOrder);

}
