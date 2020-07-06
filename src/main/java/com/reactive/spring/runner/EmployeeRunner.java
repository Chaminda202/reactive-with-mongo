package com.reactive.spring.runner;

import com.reactive.spring.model.entity.Employee;
import com.reactive.spring.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class EmployeeRunner implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    @Override
    public void run(String... args) throws Exception {
        this.employeeRepository.deleteAll().subscribe(null, null, () -> {
            Stream.of(
                    Employee.builder().id(UUID.randomUUID().toString()).name("Tom").occupation("Developer").age(27).salary(new BigDecimal(6000)).build(),
                    Employee.builder().id(UUID.randomUUID().toString()).name("Sam").occupation("Lead").age(27).salary(new BigDecimal(10000)).build(),
                    Employee.builder().id(UUID.randomUUID().toString()).name("Quanta").occupation("QA").age(27).salary(new BigDecimal(6000)).build()
            ).forEach(employee -> {
                this.employeeRepository.save(employee)
                        .subscribe(System.out::println);
            });
        });
    }
}
