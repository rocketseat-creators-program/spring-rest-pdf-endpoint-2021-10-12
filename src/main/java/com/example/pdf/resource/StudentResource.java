package com.example.pdf.resource;

import com.example.pdf.dto.StudentRequestDto;
import com.example.pdf.dto.mapper.StudentMapper;
import com.example.pdf.model.Student;
import com.example.pdf.repository.StudentRepository;
import com.example.pdf.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<Student> save(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        Student student = this.studentRepository.save(StudentMapper.fromDto(studentRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping("/report")
    public ResponseEntity<InputStreamResource> report() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=students.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(this.studentService.report()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
