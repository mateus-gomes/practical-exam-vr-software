package br.com.mateusg.practicalexam.service;

import br.com.mateusg.practicalexam.exception.NotADayOfTheMonthException;
import br.com.mateusg.practicalexam.model.Client;
import br.com.mateusg.practicalexam.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    public void create(Client client) {
        verifyIfNumberIsADayOfTheMonth(client.getInvoiceDueDate());

        clientRepository.save(client);
    }

    private void verifyIfNumberIsADayOfTheMonth(int number){
        if(number > 31){
            throw new NotADayOfTheMonthException("Should be a valid day of the month!");
        }
    }

    private Double convertToTwoDecimalPlaces(Double number){
        return Math.round(number * 100.0) / 100.0;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public boolean existsById(Long idClient) {
        return clientRepository.existsById(idClient);
    }
}
