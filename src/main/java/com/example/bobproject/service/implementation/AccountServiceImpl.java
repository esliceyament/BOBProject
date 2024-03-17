package com.example.bobproject.service.implementation;

import com.example.bobproject.entity.Account;
import com.example.bobproject.entity.Branch;
import com.example.bobproject.entity.Client;
import com.example.bobproject.mapper.AccountDTOMapper;
import com.example.bobproject.model.requestDTO.AccountReqDTO;
//import com.example.bobproject.model.requestDTO.AccountSpecification;
import com.example.bobproject.model.requestDTO.AccountSpecification;
import com.example.bobproject.model.responseDTO.AccountRespDTO;
import com.example.bobproject.repository.AccountRepository;
import com.example.bobproject.service.AccountService;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public List<AccountRespDTO> getAccount() {
        return accountRepository.findAll().stream().map(AccountDTOMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    public void addAccount(AccountReqDTO accountReqDTO) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountReqDTO.getAccountNumber());
        if (optionalAccount.isPresent()) {
            throw new IllegalStateException("Account exists!");
        }
        accountRepository.save(AccountDTOMapper.INSTANCE.toEntity(accountReqDTO));
    }

    public void deleteAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new IllegalStateException("Does not exist");
        }
        accountRepository.delete(optionalAccount.get());
    }

    private Specification<Account> getSpecification(AccountSpecification accountSpecification) {

        Specification<Account> accountNumberSpec = (root, query, criteriaBuilder) -> {
            if (!accountSpecification.getAccountNumber().isBlank()) {
                return criteriaBuilder.equal(root.get("accountNumber"), accountSpecification.getAccountNumber());
            }
            else {
                return null;
            }
        };
        Specification<Account> currencySpec = (root, query, criteriaBuilder) -> {
            if (accountSpecification.getCurrency() != null) {
                return criteriaBuilder.equal(root.get("currency"), accountSpecification.getCurrency());
            } else {
                return null;
            }
        };
        Specification<Account> clientIdSpec = (root, query, criteriaBuilder) -> {
            if (accountSpecification.getClientId() != null) {
                // Joining to the Client class
                Join<Account, Client> clientJoin = root.join("client");

                // Assuming there's a property named "id" in the Client class
                return criteriaBuilder.equal(clientJoin.get("id"), accountSpecification.getClientId());
            } else {
                return null;
            }
        };

        Specification<Account> branchIdSpec = (root, query, criteriaBuilder) -> {
            if (accountSpecification.getBranchId() != null) {
                Join<Account, Branch> branchJoin = root.join("branch");
                return criteriaBuilder.equal(branchJoin.get("id"), accountSpecification.getBranchId());
            }
            else {
                return null;
            }
        };

        return Specification.where(accountNumberSpec).and(currencySpec)
                .and(clientIdSpec).and(branchIdSpec);
    }

    public List<AccountRespDTO> getAccountData(AccountSpecification accountSpecification) {
        Specification<Account> specification = getSpecification(accountSpecification);
        return accountRepository.findAll(specification).stream().map(AccountDTOMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}
