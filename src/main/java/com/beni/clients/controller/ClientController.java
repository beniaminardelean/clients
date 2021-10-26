package com.beni.clients.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.beni.clients.model.Client;
import com.beni.clients.repository.ClientRepository;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@GetMapping(value = "/{id}")
	public Client findClientById(@PathVariable("id") int id) throws Exception {
		Optional<Client> clientOpt = clientRepository.findById(id);
		if(clientOpt.isPresent()) {
			return clientOpt.get();
		}
		throw new Exception("Clientul don`t exists");
	}
	
	@PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) throws Exception {
		if(client != null) {
			return clientRepository.save(client);
		}
		throw new Exception("Clientul must not be null");
    }
	
	@DeleteMapping("/delete/{clientId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteCourse(@PathVariable int clientId) {
		Optional<Client> clientOpt = clientRepository.findById(clientId);

	        if (clientOpt.isEmpty()) {
	            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	        }

	        clientRepository.deleteById(clientId);
	        return new ResponseEntity<Void>(HttpStatus.OK);   
	    
	}
}