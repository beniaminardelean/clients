package com.beni.clients.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.beni.clients.model.Car;
import com.beni.clients.model.Client;
import com.beni.clients.repository.CarRepository;
import com.beni.clients.repository.ClientRepository;

@Controller
@RequestMapping("/cars")
public class CarController {

	@Autowired
	CarRepository carRepository;

	@Autowired
	ClientRepository clientRepository;

	@GetMapping(value = "/{id}")
	public String findCarsByClientId(@PathVariable("id") int id, Model model) throws Exception {
		Optional<Client> clientOpt = clientRepository.findById(id);
		if (clientOpt.isPresent()) {
			Client client = clientOpt.get();
			List<Car> cars = client.getCars(); 
			model.addAttribute("cars", cars);
			model.getAttribute("cars");
			model.addAttribute("clientId", id);
			model.getAttribute("clientId");
			return "find-cars-by-client-id";
		}
		throw new Exception("This client does not have cars");
	}

	@GetMapping(value = "/add/{id}")
	public String getCarPage(@PathVariable("id") int id, Car car,Model model) {
		model.addAttribute("clientId", id);
		model.getAttribute("clientId");
		return "add-car";
	}
	
	@PostMapping("/create/{clientId}")
	@ResponseStatus(HttpStatus.CREATED)
	public String create(Car car, BindingResult result, Model model, @PathVariable("clientId") int clientId) throws Exception {
		
		Optional<Client> clientOpt = clientRepository.findById(clientId);
		if (clientOpt.isPresent()) {
			Client client = clientOpt.get();
			List<Car> cars = client.getCars();
			car.setClient(client);
			cars.add(car);
			client.setCars(cars);
			clientRepository.save(client);
			return "redirect:/clients";
		}
		throw new Exception("Car must not be null");
	}
	
	@GetMapping("/delete/{id}")
	public String deleteClient(@PathVariable("id") int id, Model model) {
		Optional<Car> car = carRepository.findById(id);
		if(car.isEmpty()) {
			throw new IllegalArgumentException("invalid car id");
		}
		carRepository.delete(car.get());
		return "redirect:/";
	}
}
