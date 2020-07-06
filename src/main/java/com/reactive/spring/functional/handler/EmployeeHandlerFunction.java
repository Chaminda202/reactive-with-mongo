package com.reactive.spring.functional.handler;

import com.reactive.spring.mapper.EmployeeMapper;
import com.reactive.spring.model.EmployeeDTO;
import com.reactive.spring.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
@AllArgsConstructor
public class EmployeeHandlerFunction {
    private final EmployeeService employeeService;

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        final Mono<EmployeeDTO> employeeDTO = serverRequest.bodyToMono(EmployeeDTO.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        fromPublisher(employeeDTO.map(EmployeeMapper::mapToEntity).flatMap(this.employeeService::save), EmployeeDTO.class)
                );
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        String empId = serverRequest.pathVariable("id");
        final Mono<EmployeeDTO> employeeDTO = serverRequest.bodyToMono(EmployeeDTO.class)
                .map(employeeDTO1 -> {
                    employeeDTO1.setId(empId);
                    return employeeDTO1;
                });
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        fromPublisher(employeeDTO.map(EmployeeMapper::mapToEntity).flatMap(this.employeeService::update), EmployeeDTO.class)
                );
    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                       this.employeeService.findAll() , EmployeeDTO.class
                );
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        String empId = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        this.employeeService.findById(empId), EmployeeDTO.class
                );
    }

    public Mono<ServerResponse> findEventById(ServerRequest serverRequest) {
        String empId = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        this.employeeService.findEmployeeByIdWithDelay(empId), EmployeeDTO.class
                );
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        String empId = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        this.employeeService.delete(empId), Void.class
                );
    }
}
