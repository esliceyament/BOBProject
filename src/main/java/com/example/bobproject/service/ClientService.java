package com.example.bobproject.service;

import com.example.bobproject.model.requestDTO.ClientReqDTO;
import com.example.bobproject.model.responseDTO.ClientRespDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    List<ClientRespDTO> getClient(PageRequest pageRequest);
    void addClient(ClientReqDTO clientReqDTO);
    void deleteClient(Long id);
}