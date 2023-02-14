package br.com.mateusg.practicalexam.repository;

import br.com.mateusg.practicalexam.model.OrderClientProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderClientProductRepository extends JpaRepository<OrderClientProduct, Long> {

    @Query(value = "SELECT * FROM order_client_product WHERE fk_product = ?1 and fk_order = ?2", nativeQuery = true)
    Optional<OrderClientProduct> findByProductAndOrder(Long idProduct, Long idOrder);

    @Query(
            value = "SELECT ocp.* FROM order_client_product ocp, client_order co " +
            "WHERE fk_client = ?1 AND fk_order = co.id_order AND co.order_date BETWEEN ?2 AND ?3",
            nativeQuery = true
    )
    List<OrderClientProduct> findByIdClientAndBetweenOrderDate(
            Long idClient,
            LocalDate previousInvoiceDueDate,
            LocalDate nextInvoiceDueDate
    );
}
