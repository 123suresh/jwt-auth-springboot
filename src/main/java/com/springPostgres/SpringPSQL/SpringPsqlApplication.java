package com.springPostgres.SpringPSQL;

import com.springPostgres.SpringPSQL.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class SpringPsqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPsqlApplication.class, args);
	}

}
