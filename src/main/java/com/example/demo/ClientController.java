package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClientController {

    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable String id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            ResponseEntity<Client> responseEntity = new ResponseEntity<>(client.get(), HttpStatus.OK);
            return responseEntity;
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/client")
    public ResponseEntity<Client> addClient(@RequestParam String firstName, @RequestParam String lastName) {
        var client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);

        var savedClient = clientRepository.save(client);
        ResponseEntity<Client> responseEntity = new ResponseEntity<>(savedClient, HttpStatus.OK);
        return responseEntity;
    }
}
