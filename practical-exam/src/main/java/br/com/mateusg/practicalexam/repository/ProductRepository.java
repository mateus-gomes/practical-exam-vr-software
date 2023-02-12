package br.com.mateusg.practicalexam.repository;

import br.com.mateusg.practicalexam.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
