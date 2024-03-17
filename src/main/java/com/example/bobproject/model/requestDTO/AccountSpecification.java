package com.example.bobproject.model.requestDTO;

import com.example.bobproject.enums.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class AccountSpecification {

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private Currency currency;


    private Long clientId;

    private Long branchId;

}
