package com.example.bobproject.model.responseDTO;

import lombok.Data;

@Data
public class AccountRespDTO {
    private Long id;

    private String accountNumber;

    private String currency;

    private Double amount;

    private String iban;

    private Long clientId;

    private Long branchId;

}