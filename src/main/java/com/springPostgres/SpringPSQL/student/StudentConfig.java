package com.springPostgres.SpringPSQL.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student john = new Student(
                    1,
                    "John",
                    "john@gmail.com",
                    LocalDate.of(2012,2,5)
            );

            Student jasmine = new Student(
                    1,
                    "Jasmine",
                    "Jasmine@gmail.com",
                    LocalDate.of(2009,2,5)
            );

            repository.saveAll(
                    List.of(john, jasmine)
            );
        };
    }
}
