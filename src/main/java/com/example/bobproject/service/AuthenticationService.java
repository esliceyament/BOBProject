package com.example.bobproject.service;

import com.example.bobproject.model.requestDTO.AuthenticationRequestDTO;
import com.example.bobproject.model.requestDTO.RegisterRequestDTO;
import com.example.bobproject.model.responseDTO.AuthenticationResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthenticationResponseDTO register(RegisterRequestDTO request);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
}
