package com.example.pdf;

import com.example.pdf.model.School;
import com.example.pdf.model.Student;
import com.example.pdf.repository.SchoolRepository;
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
    CommandLineRunner run(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        return args -> {
            School ignite = new School("Ignite");
            School discover = new School("Discover");
            School expertsClub = new School("ExpertsClub");

            schoolRepository.save(ignite);
            schoolRepository.save(discover);
            schoolRepository.save(expertsClub);

            studentRepository.save(new Student("Pedro", "pedro@gmail.com", "123456", LocalDate.of(1992, 8, 28), discover));
            studentRepository.save(new Student("Marcos", "marcos@gmail.com", "123456", LocalDate.of(1990, 2, 11), discover));
            studentRepository.save(new Student("Paulo", "paulo@gmail.com", "123456", LocalDate.of(1988, 3, 12), discover));
            studentRepository.save(new Student("Vinicios", "vinicios@gmail.com", "123456", LocalDate.of(1996, 7, 18), discover));

            studentRepository.save(new Student("Jorge", "jorge@gmail.com", "123456", LocalDate.of(2000, 9, 21), ignite));
            studentRepository.save(new Student("Aline", "aline@gmail.com", "123456", LocalDate.of(1997, 10, 27), ignite));
            studentRepository.save(new Student("Paula", "paula@gmail.com", "123456", LocalDate.of(1995, 11, 2), ignite));

            studentRepository.save(new Student("Ana", "ana@gmail.com", "123456", LocalDate.of(1993, 3, 1), expertsClub));
            studentRepository.save(new Student("Marcia", "marcia@gmail.com", "123456", LocalDate.of(1964, 8, 9), expertsClub));
            studentRepository.save(new Student("Marta", "marta@gmail.com", "123456", LocalDate.of(1996, 1, 5), expertsClub));
            studentRepository.save(new Student("Yuri", "yuri@gmail.com", "123456", LocalDate.of(2001, 3, 25), expertsClub));
            studentRepository.save(new Student("Rodrigo", "rodrigo@gmail.com", "123456", LocalDate.of(2003, 5, 19), expertsClub));
            studentRepository.save(new Student("Marcelo", "marcelo@gmail.com", "123456", LocalDate.of(1995, 7, 11), expertsClub));
        };
    }

}
