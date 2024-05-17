package com.crud.webflux.demowebflux.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    @NotBlank(message = "name is mandatory")
    private String name;
    @Min(value = 1, message = "price must be greater than zero")
    private Double price;
}
