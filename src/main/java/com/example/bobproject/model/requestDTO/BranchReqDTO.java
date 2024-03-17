package com.example.bobproject.model.requestDTO;

import com.example.bobproject.enums.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BranchReqDTO {

    @NotBlank(message = "Name is required")
    @Size(min=5, max=20)
    private String name;

    @NotNull
    private Integer locationId;

    @NotBlank
    private String code;

}