package com.reactive.spring.service.impl;

import com.reactive.spring.mapper.EmployeeMapper;
import com.reactive.spring.model.EmployeeDTO;
import com.reactive.spring.model.EmployeeEventDTO;
import com.reactive.spring.model.entity.Employee;
import com.reactive.spring.repository.EmployeeRepository;
import com.reactive.spring.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Mono<EmployeeDTO> save(Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        return this.employeeRepository
                .save(employee)
                .map(EmployeeMapper::mapToDTO);
    }

    @Override
    public Mono<EmployeeDTO> update(Employee employee) {
        return this.employeeRepository
                .save(employee)
                .map(EmployeeMapper::mapToDTO);
    }

    @Override
    public void delete(String empId) {
        this.employeeRepository.deleteById(empId);
    }

    @Override
    public Flux<EmployeeDTO> findAll() {
        return this.employeeRepository
                .findAll()
                .map(EmployeeMapper::mapToDTO);
    }

    @Override
    public Mono<EmployeeDTO> findById(String empId) {
        return this.employeeRepository
                .findById(empId)
                .map(EmployeeMapper::mapToDTO);
    }

    @Override
    public Flux<EmployeeEventDTO> findEmployeeByIdWithDelay(String empId) {
        return this.employeeRepository.findById(empId)
                .map(EmployeeMapper::mapToDTO)
                .flatMapMany(employeeDTO -> {
                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));

                    Flux<EmployeeEventDTO> employeeEventDTOFlux = Flux.fromStream(
                            Stream.generate(() -> new EmployeeEventDTO(employeeDTO, new Date()))
                    );
                    return Flux.zip(interval, employeeEventDTOFlux) // merge two flux
                            .map(Tuple2::getT2);
                });
    }
}
