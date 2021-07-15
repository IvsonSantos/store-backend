package com.store.controller;

import com.store.dto.ClientDTO;
import com.store.entity.Client;
import com.store.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<Client> find(@PathVariable Long id) {
        Client client = service.find(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping()
    public ResponseEntity<Client> save(@Valid @RequestBody ClientDTO clientDto) {
        Client obj = service.fromDto(clientDto);
        obj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> list = this.service.findAll();
        List<ClientDTO> listDTO = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Long id) {
        Client obj = service.fromDto(clientDTO);
        obj.setId(id);
        this.service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
