package com.example.bobproject.model.requestDTO;

import com.example.bobproject.enums.AuthorityRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientReqDTO {
    @NotBlank(message = "Enter the name!")
    @Size(min=2, max=15)
    private String name;

    @NotBlank(message = "Enter the surname!")
    @Size(min=2, max=15)
    private String surname;

    @NotBlank(message = "Enter the username!")
    @Size(min=4, max=15)
    private String username;

    @NotBlank(message = "Email is required!")
    @Email
    private String email;

    @NotBlank(message = "Enter password!")
    @Size(min=4, max=20)
    private String password;

    @Pattern(regexp = "^\\+994\\d{9}$", message = "Invalid phone format")
    private String phone;

    @NotNull
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private AuthorityRole authorityRole;
}