package com.elmo.springmongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class SpringMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongodbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address(
                    "England",
                    "London",
                    "NE9"
            );
            String email = "jahmed@gmail.com";
            Student student = new Student(
                    "Jamila",
                    "Ahmed",
                    email,
                    Gender.FEMALE,
                    address,
                    List.of("Computer Science"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );
            studentRepository.findStudentByEmail(email).ifPresentOrElse(s -> {
                System.out.println(s + " already exists");
            }, () -> {
                System.out.println("Inserting student " + student);
                studentRepository.insert(student);
            });
        };
    }
}
