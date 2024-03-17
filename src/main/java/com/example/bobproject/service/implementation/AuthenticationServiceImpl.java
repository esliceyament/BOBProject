package com.example.bobproject.service.implementation;

import com.example.bobproject.entity.Client;
import com.example.bobproject.enums.AuthorityRole;
import com.example.bobproject.exception.DuplicateUsernameException;
import com.example.bobproject.model.requestDTO.AuthenticationRequestDTO;
import com.example.bobproject.model.requestDTO.RegisterRequestDTO;
import com.example.bobproject.model.responseDTO.AuthenticationResponseDTO;
import com.example.bobproject.repository.ClientRepository;
import com.example.bobproject.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponseDTO register(RegisterRequestDTO request) {

        Optional<Client> client = clientRepository.findClientByUsername(request.getUsername());
        if (client.isPresent()){
            throw new DuplicateUsernameException(String.format("User %s already exists",request.getUsername()));
        }

        var user = Client.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .dob(request.getDob())
                .authorityRole(AuthorityRole.CLIENT)
                .build();
        clientRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        Authentication authentication  = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        log.info("AUTHENTICATION -> {}", authentication);
        var user = clientRepository.findClientByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder().token(jwtToken).build();    }


}
