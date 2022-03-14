package com.beni.clients.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beni.clients.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
	

}
