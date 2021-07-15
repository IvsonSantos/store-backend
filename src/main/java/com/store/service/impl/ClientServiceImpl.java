package com.store.service.impl;

import com.store.dto.ClientDTO;
import com.store.entity.Client;
import com.store.repository.ClientRepository;
import com.store.service.ClientService;
import com.store.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client find(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException(
                "Client with id " + id + " not found"
        ));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        client.setId(null);
        client = clientRepository.save(client);
        return client;
    }

    @Override
    public Client update(Client client) {
        Client newObj = find(client.getId());
        updateData(newObj, client);
        return clientRepository.save(newObj);
    }

    private void updateData(Client newClient, Client oldClient) {
        newClient.setFirstName(oldClient.getFirstName());
        newClient.setLastName(oldClient.getLastName());
        newClient.setEmail(oldClient.getEmail());
    }

    @Override
    public void delete(Long id) {
        find(id);
        clientRepository.deleteById(id);
    }

    public Client fromDto(ClientDTO dto) {
        Client client = new Client(dto.getId(),dto.getFirst_name(), dto.getLast_name(), dto.getEmail());
        return client;
    }

}
