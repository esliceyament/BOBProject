package com.example.bobproject.controller;

import com.example.bobproject.model.requestDTO.AccountReqDTO;
import com.example.bobproject.model.requestDTO.AccountSpecification;
import com.example.bobproject.model.responseDTO.AccountRespDTO;
import com.example.bobproject.service.implementation.AccountServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;
    private final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @GetMapping
    public List<AccountRespDTO> getAccount() {
        LOGGER.info("Inside getAccount of AccountApi");
        return accountService.getAccount();
    }

    @GetMapping("/specification")
    public List<AccountRespDTO> ByEqual(AccountSpecification accountSpecification) {
        return accountService.getAccountData(accountSpecification);
    }

    @PostMapping
    public void addAccount(@Valid @RequestBody AccountReqDTO accountReqDTO) {
        LOGGER.info("Inside addAccount of AccountApi");
        accountService.addAccount(accountReqDTO);
    }

    @DeleteMapping(path = "{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long accountId) {
        LOGGER.info("Inside deleteAccount of AccountApi");
        accountService.deleteAccount(accountId);
    }
}
