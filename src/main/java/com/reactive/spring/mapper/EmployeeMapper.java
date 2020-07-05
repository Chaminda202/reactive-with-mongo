package com.reactive.spring.mapper;

import com.reactive.spring.model.EmployeeDTO;
import com.reactive.spring.model.entity.Employee;

public class EmployeeMapper {
    private EmployeeMapper(){
    }

    public static Employee mapToEntity(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .id(employeeDTO.getId())
                .name(employeeDTO.getName())
                .occupation(employeeDTO.getOccupation())
                .salary(employeeDTO.getSalary())
                .age(employeeDTO.getAge())
                .build();
    }

    public static EmployeeDTO mapToDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .occupation(employee.getOccupation())
                .salary(employee.getSalary())
                .age(employee.getAge())
                .build();
    }
}
