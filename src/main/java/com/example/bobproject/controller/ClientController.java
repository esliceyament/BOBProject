package com.example.bobproject.controller;

import com.example.bobproject.model.requestDTO.ClientReqDTO;
import com.example.bobproject.model.responseDTO.ClientRespDTO;
import com.example.bobproject.service.implementation.ClientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServiceImpl clientService;
    private final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @GetMapping
    public List<ClientRespDTO> getClient(@RequestParam(defaultValue = "0")int pageNumber,
                                         @RequestParam(defaultValue = "10")int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        LOGGER.info("Inside getClient of ClientApi");
        return clientService.getClient(pageRequest);
    }

    @PostMapping
    public void addClient(@Valid @RequestBody ClientReqDTO clientReqDTO) {
        LOGGER.info("Inside addClient of ClientApi");
        clientService.addClient(clientReqDTO);
    }

    @DeleteMapping(path="{clientId}")
    public void deleteClient(@PathVariable("clientId") Long clientId) {
        LOGGER.info("Inside deleteClient of ClientApi");
        clientService.deleteClient(clientId);
    }
}
