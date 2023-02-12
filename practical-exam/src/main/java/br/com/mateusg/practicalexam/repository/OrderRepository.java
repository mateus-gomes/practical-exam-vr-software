package br.com.mateusg.practicalexam.repository;

import br.com.mateusg.practicalexam.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
