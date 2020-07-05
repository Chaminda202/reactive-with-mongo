package com.reactive.spring.model;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private String id;
    private String name;
    private String occupation;
    private BigDecimal salary;
    private int age;
}
