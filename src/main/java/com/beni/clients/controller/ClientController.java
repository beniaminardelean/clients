package com.beni.clients.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beni.clients.model.Client;
import com.beni.clients.repository.ClientRepository;

@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@GetMapping(value = "/{id}")
	public Client findClientById(@PathVariable("id") int id) throws Exception {
		Optional<Client> clientOpt = clientRepository.findById(id);
		if (clientOpt.isPresent()) {
			return clientOpt.get();
		}
		throw new Exception("Client don`t exists");
	}

	@GetMapping(value = "/add")
	public String getClientPage(Client client) {
		return "add-client";
	}

	@GetMapping(value = "edit/{id}")
	public String getEditPage(@PathVariable("id") int id, Model model) throws Exception {
		Optional<Client> clientOpt = clientRepository.findById(id);
		if (clientOpt.isPresent()) {
			Client client = clientOpt.get();
			model.addAttribute("client", client);
			return "update-client";
		}
		throw new Exception("Client don`t exists");
	}

	@PostMapping("/update/{id}")
	public String updateClient(@PathVariable("id") int id, Client client, BindingResult result, Model model) {
		if (result.hasErrors()) {
			client.setId(id);

			return "update-client";
		}

		clientRepository.save(client);
		return "redirect:/clients";
	}

	@GetMapping
	public String showClientsList(Model model) {
		model.addAttribute("clients", clientRepository.findAll());
		model.getAttribute("clients");
		return "find-all-clients";
	}

	@PostMapping("/create")
	public String create(Client client, BindingResult result, Model model) throws Exception {
		if (client != null) {
			clientRepository.save(client);
			return "redirect:/clients";
		}
		throw new Exception("Client must not be null");
	}

	@GetMapping("/delete/{id}")
	public String deleteClient(@PathVariable("id") int id, Model model) {
		Optional<Client> client = clientRepository.findById(id);
		if(client.isEmpty()) {
			throw new IllegalArgumentException("invalid client id");
		}
		clientRepository.delete(client.get());
		return "redirect:/";

	}

}
