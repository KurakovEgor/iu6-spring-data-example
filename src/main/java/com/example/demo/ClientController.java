package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ClientController {

    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable String id, HttpSession httpSession) {
        var userId = (String) httpSession.getAttribute("userId");
        if (userId == null) {
            //ошибка
        } else {

        }

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

    @PostMapping("/signup")
    public ResponseEntity<Client> addClient(@RequestBody Client client, HttpSession httpSession) {
        System.out.println(client.toString());
        var savedClient = clientRepository.save(client);
        ResponseEntity<Client> responseEntity = new ResponseEntity<>(savedClient, HttpStatus.OK);

        httpSession.setAttribute("userId", client.getId());
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Client client, HttpSession httpSession) {
        var savedClient = clientRepository.findById(client.getId());
        var isPasswordCorrect = savedClient.map(clientFromDb -> clientFromDb.getPassword().equals(client.getPassword())).orElse(false);
        ResponseEntity<Void> responseEntity;
        if (isPasswordCorrect) {
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
            httpSession.setAttribute("userId", client.getId());
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return responseEntity;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession httpSession) {
        httpSession.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
