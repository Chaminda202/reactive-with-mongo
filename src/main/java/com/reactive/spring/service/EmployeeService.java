package com.reactive.spring.service;

import com.reactive.spring.model.EmployeeDTO;
import com.reactive.spring.model.EmployeeEventDTO;
import com.reactive.spring.model.entity.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<EmployeeDTO> save(Employee employee);
    Mono<EmployeeDTO> update(Employee employee);
    void delete(String empId);
    Flux<EmployeeDTO> findAll();
    Mono<EmployeeDTO> findById(String empId);
    Flux<EmployeeEventDTO> findEmployeeByIdWithDelay(String empId);

}
