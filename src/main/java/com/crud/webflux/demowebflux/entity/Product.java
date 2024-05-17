package com.crud.webflux.demowebflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    private Long id;
    private String name;
    private Double price;
}
