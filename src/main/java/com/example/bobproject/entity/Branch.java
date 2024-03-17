package com.example.bobproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min=5, max=20)
    private String name;

    @NotNull
    private Integer LocationId;

    @NotBlank
    private String code;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Account> account;

}