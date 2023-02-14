package br.com.mateusg.practicalexam.controller;

import br.com.mateusg.practicalexam.model.Client;
import br.com.mateusg.practicalexam.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientControllerTest {

    @Autowired
    private ClientController clientController;

    @MockBean
    private ClientService clientService;

    @MockBean
    private BindingResult bindingResult;

    @Test
    @DisplayName("GET /clients - Should return 200 when is successful")
    void findAllClients() {
        ResponseEntity response = clientController.findAllClients();

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("POST /clients - Should return 422 when some attribute is not valid")
    void createClientInvalidAttribute() {
        Client client = new Client();

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity response = clientController.createClient(client, bindingResult);

        assertEquals(422, response.getStatusCodeValue());

    }

    @Test
    @DisplayName("POST /clients - Should return 400 when id is already in use")
    void createClientIdAlreadyInUse() {
        Client client = buildClient();

        Mockito.when(clientService.existsById(client.getIdClient())).thenReturn(true);

        ResponseEntity response = clientController.createClient(client, bindingResult);

        assertEquals(400, response.getStatusCodeValue());

    }

    @Test
    @DisplayName("POST /clients - Should return 201 when is created successfully")
    void createClientSuccessfully() {
        Client client = buildClient();

        ResponseEntity response = clientController.createClient(client, bindingResult);

        assertEquals(201, response.getStatusCodeValue());

    }

    private Client buildClient(){
        Client client = new Client();
        client.setIdClient(1L);
        client.setClientName("Foo Bar");
        client.setClientLimit(1000.0);
        client.setInvoiceDueDate((byte) 5);

        return client;
    }
}