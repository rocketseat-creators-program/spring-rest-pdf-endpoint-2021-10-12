package com.example.pdf.service;

import com.example.pdf.report.CustomReport;
import com.example.pdf.report.CustomText;
import com.example.pdf.repository.StudentRepository;
import com.example.pdf.util.DateUtils;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.layout.property.TextAlignment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public CustomReport report() {
        CustomReport customReport = new CustomReport();
        customReport.landscape();

        customReport.addParagraph(CustomText.builder()
                .fontSize(28f)
                .value("Students List")
                .font(StandardFonts.COURIER_BOLD)
                .textAlignment(TextAlignment.CENTER)
                .build());

        customReport.addNewLine();
        customReport.addParagraph("TEEEEEEEEEEEEEEESTE");
        customReport.openTable(5);
        customReport.addTableHeaders("NAME", "EMAIL", "AGE", "BIRTHDAY", "CREATED AT");

        this.studentRepository.findAll().forEach(student -> {
            List<Object> rowColumns = new ArrayList();
            rowColumns.add(student.getName());
            rowColumns.add(student.getEmail());
            rowColumns.add(DateUtils.age(student.getBirthday()));
            rowColumns.add(DateUtils.format(student.getBirthday(), "dd/MM/yyyy"));
            rowColumns.add(DateUtils.format(student.getCreatedAt(), "dd/MM/yyyy HH:mm"));

            customReport.addTableRow(rowColumns);
        });


        customReport.closeTable();
        customReport.closeDocument();

        return customReport;
    }

}
