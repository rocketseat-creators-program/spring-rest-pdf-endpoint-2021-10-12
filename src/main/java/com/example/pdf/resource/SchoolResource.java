package com.example.pdf.resource;

import com.example.pdf.model.School;
import com.example.pdf.repository.SchoolRepository;
import com.example.pdf.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/schools")
public class SchoolResource {

    private SchoolService schoolService;
    private SchoolRepository schoolRepository;

    @GetMapping
    public List<School> findAll() {
        return this.schoolRepository.findAll();
    }

    @GetMapping("/report")
    public ResponseEntity<InputStreamResource> report() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=students.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(this.schoolService.report()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
