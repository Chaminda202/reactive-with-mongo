package com.reactive.spring.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Employee {
    @Id
    private String id;
    private String name;
    private String occupation;
    private BigDecimal salary;
    private int age;
}
