package com.example.pdf.dto.mapper;

import com.example.pdf.dto.StudentRequestDto;
import com.example.pdf.model.Student;

public class StudentMapper {

    public static Student fromDto(StudentRequestDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());
        student.setBirthday(dto.getBirthday());

        return student;
    }

}
