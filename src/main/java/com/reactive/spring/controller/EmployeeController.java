package com.reactive.spring.controller;

import com.reactive.spring.mapper.EmployeeMapper;
import com.reactive.spring.model.EmployeeDTO;
import com.reactive.spring.model.EmployeeEventDTO;
import com.reactive.spring.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/reactiveMongo/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public Mono<EmployeeDTO> save(@RequestBody EmployeeDTO employeeDTO) {
        return this.employeeService.save(EmployeeMapper.mapToEntity(employeeDTO));
    }

    @PutMapping(value = "/{id}")
    public Mono<EmployeeDTO> update(@PathVariable(value = "id") String empId,  @RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(empId);
        return this.employeeService.save(EmployeeMapper.mapToEntity(employeeDTO));
    }

    @GetMapping
    public Flux<EmployeeDTO> findAll(){
        return this.employeeService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<EmployeeDTO> findById(@PathVariable(value = "id") String empId) {
        return this.employeeService.findById(empId);
    }

    @GetMapping(value = "/{id}/event", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<EmployeeEventDTO> findEventById(@PathVariable(value = "id") String empId) {
        return this.employeeService.findEmployeeByIdWithDelay(empId);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") String empId) {
        this.employeeService.delete(empId);
    }
}
