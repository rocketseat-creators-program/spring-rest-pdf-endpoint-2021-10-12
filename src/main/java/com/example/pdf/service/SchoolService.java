package com.example.pdf.service;

import com.example.pdf.repository.SchoolRepository;
import com.example.pdf.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SchoolService {

    private SchoolRepository schoolRepository;
    private StudentRepository studentRepository;

}
