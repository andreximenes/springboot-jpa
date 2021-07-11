package com.alx.studyproject.controller;

import com.alx.studyproject.domain.Client;
import com.alx.studyproject.domain.Phone;
import com.alx.studyproject.http.ClientRequest;
import com.alx.studyproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> listAll(){
        return clientService.getAllClients();
    }

    @GetMapping(path = "/{id}")
    public Client getById(@PathVariable Integer id){
        return clientService.findById(id).get();
    }

    @PostMapping
    public Client save(@RequestBody ClientRequest client){
        return clientService.save(client, true).get();
    }

    @PutMapping
    public Client update(@RequestBody ClientRequest client){
        return clientService.save(client, false).get();
    }

    @PutMapping(path = "/phone")
    public Client updateClientPhone(@Valid @RequestBody Phone phone){
        return clientService.updatePhone(phone).get();
    }

}
