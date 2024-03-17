package com.example.bobproject.model.responseDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientRespDTO {
    private Long id;

    private String username;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private LocalDate dob;

}