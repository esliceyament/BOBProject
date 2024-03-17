package com.example.bobproject.model.requestDTO;

import com.example.bobproject.enums.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountReqDTO {
    private String accountNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotBlank(message = "IBAN is required")
    private String iban;

    @NotNull
    private Long clientId;

    @NotNull
    private Long branchId;

}