package com.example.pdf.service;

import com.example.pdf.report.CustomReport;
import com.example.pdf.report.CustomText;
import com.example.pdf.repository.StudentRepository;
import com.example.pdf.util.DateUtils;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.property.TextAlignment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public ByteArrayInputStream report() {
        List<Object[]> objectsList = this.studentRepository.findAll().stream().map(student -> {
            List<Object> objects = new ArrayList();
                objects.add(student.getName());
                objects.add(student.getEmail());
                objects.add(DateUtils.age(student.getBirthday()));
                objects.add(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(student.getBirthday()));
                objects.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(student.getCreatedAt()));
            return objects.toArray();
        }).collect(Collectors.toList());

        CustomText title = CustomText.builder()
                .fontSize(24f)
                .value("Students List")
                .font(StandardFonts.COURIER_BOLD)
                .textAlignment(TextAlignment.CENTER)
                .build();

        return CustomReport.builder()
                .landscape(PageSize.A4)
                .addParagraph(title)
                .addNewLine()
                .openTable(5)
                .addTableHeaders("NAME", "EMAIL", "AGE", "BIRTHDAY", "CREATED AT")
                .addTableRows(objectsList)
                .closeTable()
                .build();
    }

}
