package com.example.pdf.resource;

import com.example.pdf.model.Student;
import com.example.pdf.repository.StudentRepository;
import com.example.pdf.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentResource {

    private StudentService studentService;
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @GetMapping("/report")
    public ResponseEntity<InputStreamResource> report() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=students.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
