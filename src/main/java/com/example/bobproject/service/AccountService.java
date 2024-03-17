package com.example.bobproject.service;

import com.example.bobproject.model.requestDTO.AccountReqDTO;
import com.example.bobproject.model.responseDTO.AccountRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<AccountRespDTO> getAccount();
    void addAccount(AccountReqDTO accountReqDTO);
    void deleteAccount(Long id);
}