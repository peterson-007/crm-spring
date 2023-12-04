package org.ultimacrm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ultimacrm.dtos.ClientInputDTO;
import org.ultimacrm.models.Client;
import org.ultimacrm.service.ClientService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //client/{id}
    @GetMapping("{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") int idClient){
        return ResponseEntity.ok(clientService.getClientById(idClient));
    }

    @GetMapping
    public ResponseEntity<Iterable<Client>> getClientService() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @PostMapping
    public ResponseEntity addClient(@Valid @RequestBody ClientInputDTO client){
        this.clientService.addClient(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
        //O corpo da resposta está vazio (new ResponseEntity<>(...)), pois é a prática comum
        // para uma resposta de criação é retornar apenas o status HTTP correspondente, sem dados adicionais.
    }

}
