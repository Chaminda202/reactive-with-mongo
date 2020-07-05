package com.reactive.spring.model;

import lombok.*;

import java.util.Date;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEventDTO {
    private EmployeeDTO employeeDTO;
    private Date date;
}
