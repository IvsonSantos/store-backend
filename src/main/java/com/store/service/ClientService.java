package com.store.service;

import com.store.dto.ClientDTO;
import com.store.entity.Client;

import java.util.List;

public interface ClientService {

    Client find(Long id);
    List<Client> findAll();
    Client save(Client client);
    Client update(Client client);
    void delete(Long id);
    Client fromDto(ClientDTO dto);

}
