package com.reactive.spring.functional.router;

import com.reactive.spring.functional.handler.EmployeeHandlerFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RouterFunctionConfig {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(EmployeeHandlerFunction employeeHandlerFunction) {
        return RouterFunctions
                .route(GET("/functional/reactiveMongo/employees").and(accept(MediaType.APPLICATION_JSON)), employeeHandlerFunction::findAll)
                .andRoute(GET("/functional/reactiveMongo/employees/{id}").and(accept(MediaType.APPLICATION_JSON)), employeeHandlerFunction::findById)
                .andRoute(GET("/functional/reactiveMongo/employees/{id}/event").and(accept(MediaType.APPLICATION_JSON)), employeeHandlerFunction::findEventById)
                .andRoute(POST("/functional/reactiveMongo/employees").and(accept(MediaType.APPLICATION_JSON)), employeeHandlerFunction::save)
                .andRoute(PUT("/functional/reactiveMongo/employees/{id}").and(accept(MediaType.APPLICATION_JSON)), employeeHandlerFunction::update)
                .andRoute(DELETE("/functional/reactiveMongo/employees/{id}").and(accept(MediaType.APPLICATION_JSON)), employeeHandlerFunction::delete);
    }
}
