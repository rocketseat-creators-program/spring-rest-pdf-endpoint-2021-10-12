package com.example.pdf.repository;

import com.example.pdf.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, String> {

}
