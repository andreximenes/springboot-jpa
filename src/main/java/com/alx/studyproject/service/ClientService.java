package com.alx.studyproject.service;

import com.alx.studyproject.domain.Client;
import com.alx.studyproject.domain.Phone;
import com.alx.studyproject.http.ClientRequest;
import com.alx.studyproject.repository.ClientRepository;
import com.alx.studyproject.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public ClientService(ClientRepository repository, PhoneRepository phoneRepository) {
        this.repository = repository;
        this.phoneRepository = phoneRepository;
    }

    public List<Client> getAllClients(){
        return repository.findAll();
    }

    public Optional<Client> findById(Integer id){
        return repository.findById(id);
    }

    @Transactional
    public Optional<Client> save(ClientRequest request, boolean isInsert) {
        Client newClient = repository.save(Client.builder()
                .firstName(request.getFirstName())
                .last_name(request.getLast_name())
                .build());

        if(isInsert && !request.getPhones().isEmpty()) {
            request.getPhones().forEach(phoneNumber -> {
                phoneRepository.save(Phone.builder()
                        .clientId(newClient.getClientId())
                        .phoneNumber(phoneNumber)
                        .build());
            });
        }
        return repository.findById(newClient.getClientId());
    }

    public Optional<Client> updatePhone(Phone phone) {
        phoneRepository.save(phone);
        return repository.findById(phone.getClientId());
    }
}
