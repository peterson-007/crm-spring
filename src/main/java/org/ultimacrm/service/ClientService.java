package org.ultimacrm.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.ultimacrm.dtos.ClientInputDTO;
import org.ultimacrm.exceptions.ResourceNotFoundException;
import org.ultimacrm.models.Client;
import org.ultimacrm.repositories.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Iterable<Client> getClients() {
        return this.clientRepository.findAll();
    }

    //O ModelMapper- biblioteca Java que facilita a conversão de objetos de um tipo para outro.
    //Neste contexto, está sendo usado para mapear os dados de um objeto do tipo ClientInputDTO
    // para um objeto do tipo Client.
    public void addClient(ClientInputDTO client){
        ModelMapper mapper = new ModelMapper();
        Client clientToPersist = mapper.map(client, Client.class);
        this.clientRepository.save(clientToPersist);
    }

    public Client getClientById(int idClient) {
        Optional<Client> client = this.clientRepository.findById(idClient);
        if (client.isEmpty()) {
            throw new ResourceNotFoundException("Client do not exist");
        }
        return client.get();
    }
}
