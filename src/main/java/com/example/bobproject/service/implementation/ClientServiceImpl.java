package com.example.bobproject.service.implementation;

import com.example.bobproject.entity.Client;
import com.example.bobproject.enums.AuthorityRole;
import com.example.bobproject.mapper.ClientDTOMapper;
import com.example.bobproject.model.requestDTO.ClientReqDTO;
import com.example.bobproject.model.responseDTO.ClientRespDTO;
import com.example.bobproject.repository.ClientRepository;
import com.example.bobproject.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public List<ClientRespDTO> getClient(PageRequest pageRequest) {
        Page<Client> clientPage = clientRepository.findAll(pageRequest);
        return clientPage.getContent().stream().map(ClientDTOMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    public void addClient(ClientReqDTO clientReqDTO) {
        Optional<Client> clientOptional = clientRepository.findClientByUsername(clientReqDTO.getUsername());
        if (clientOptional.isPresent()) {
            throw new IllegalStateException("Client with this username exists!");
        }
        clientReqDTO.setAuthorityRole(AuthorityRole.CLIENT);
        clientRepository.save(ClientDTOMapper.INSTANCE.toEntity(clientReqDTO));
    }

    public void deleteClient(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isEmpty()) {
            throw new IllegalStateException("Does not exist!");
        }
        clientRepository.delete(optionalClient.get());

    }
}
