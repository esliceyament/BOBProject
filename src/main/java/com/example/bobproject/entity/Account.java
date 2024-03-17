package com.example.bobproject.entity;

import com.example.bobproject.enums.Currency;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotBlank(message = "IBAN is required")
    private String iban;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;

    @JoinColumn(name="branch_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Branch branch;

}