package br.com.mateusg.practicalexam.repository;

import br.com.mateusg.practicalexam.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT DISTINCT co.* FROM client_order co, order_client_product ocp WHERE " +
            "co.id_order = ocp.fk_order " +
            "AND (ocp.fk_client = ?1 OR ?1 IS NULL) " +
            "and (ocp.fk_product = ?2 OR ?2 IS NULL) " +
            "and (co.order_status = ?3 OR ?3 IS NULL) " +
            "and (co.order_date = cast(?4 as date) OR cast(?4 as date) IS NULL)"
            , nativeQuery = true)
    List<Order> findAllFiltered(
            Long idClient,
            Long idProduct,
            String orderStatus,
            String orderDate
    );

}
