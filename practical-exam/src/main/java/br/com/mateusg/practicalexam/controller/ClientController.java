package br.com.mateusg.practicalexam.controller;

import br.com.mateusg.practicalexam.handler.ErrorHandler;
import br.com.mateusg.practicalexam.model.Client;
import br.com.mateusg.practicalexam.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ErrorHandler errorHandler = new ErrorHandler();

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity findAllClients(){
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createClient(@Valid @RequestBody Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(422).body(errorHandler.buildErrorMessage(bindingResult));
        }

        if(clientService.existsById(client.getIdClient())){
            return ResponseEntity.status(400).body("Client id is already in use.");
        } else {
            clientService.create(client);
            return ResponseEntity.status(201).body(client);
        }
    }

}
