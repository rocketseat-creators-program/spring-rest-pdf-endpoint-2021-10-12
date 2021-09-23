package com.example.pdf;

import com.example.pdf.model.Student;
import com.example.pdf.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SpringRestPdfEndpointApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestPdfEndpointApplication.class, args);
    }

    @Bean
    CommandLineRunner run(StudentRepository studentRepository) {
        return args -> {
            studentRepository.save(new Student("Pedro", "pedro@gmail.com", "123456", LocalDate.of(1992, 8, 28)));
            studentRepository.save(new Student("Marcos", "marcos@gmail.com", "123456", LocalDate.of(1990, 2, 11)));
            studentRepository.save(new Student("Paulo", "paulo@gmail.com", "123456", LocalDate.of(1988, 3, 12)));
            studentRepository.save(new Student("Vinicios", "vinicios@gmail.com", "123456", LocalDate.of(1996, 7, 18)));
            studentRepository.save(new Student("Jorge", "jorge@gmail.com", "123456", LocalDate.of(2000, 9, 21)));
            studentRepository.save(new Student("Aline", "aline@gmail.com", "123456", LocalDate.of(1997, 10, 27)));
            studentRepository.save(new Student("Paula", "paula@gmail.com", "123456", LocalDate.of(1995, 11, 2)));
            studentRepository.save(new Student("Ana", "ana@gmail.com", "123456", LocalDate.of(1993, 3, 1)));
            studentRepository.save(new Student("Marcia", "marcia@gmail.com", "123456", LocalDate.of(1964, 8, 9)));
            studentRepository.save(new Student("Marta", "marta@gmail.com", "123456", LocalDate.of(1996, 8, 9)));
        };
    }

}
