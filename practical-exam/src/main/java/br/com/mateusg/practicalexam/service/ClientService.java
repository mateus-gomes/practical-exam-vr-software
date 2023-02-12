package br.com.mateusg.practicalexam.service;

import br.com.mateusg.practicalexam.model.Client;
import br.com.mateusg.practicalexam.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public void create(Client client) {
        clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public boolean existsById(Long idClient) {
        return clientRepository.existsById(idClient);
    }
}
