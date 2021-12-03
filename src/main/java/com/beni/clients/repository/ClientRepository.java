package com.beni.clients.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beni.clients.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	static Optional<Client> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
