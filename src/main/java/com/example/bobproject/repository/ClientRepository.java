package com.example.bobproject.repository;

import com.example.bobproject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByUsername(String username);
    Optional<Client> findClientByEmail(String email);
    Optional<Client> findClientByPhone(String phone);

}
