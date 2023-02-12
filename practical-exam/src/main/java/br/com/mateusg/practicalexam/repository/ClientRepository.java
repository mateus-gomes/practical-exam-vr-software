package br.com.mateusg.practicalexam.repository;

import br.com.mateusg.practicalexam.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
