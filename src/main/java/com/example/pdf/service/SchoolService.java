package com.example.pdf.service;

import com.example.pdf.report.CustomReport;
import com.example.pdf.report.CustomText;
import com.example.pdf.repository.SchoolRepository;
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
public class SchoolService {

    private SchoolRepository schoolRepository;
    private StudentRepository studentRepository;

    public CustomReport report() {
        CustomReport customReport = new CustomReport();

        customReport.addParagraph(CustomText.builder()
                .fontSize(28f)
                .value("Schools List")
                .font(StandardFonts.COURIER_BOLD)
                .textAlignment(TextAlignment.CENTER)
                .build());

        customReport.addNewLine();
        customReport.openTable(3);
        customReport.addTableHeaders("NAME", "STUDENTS", "CREATED AT");

        this.schoolRepository.findAll().forEach(school -> {
            List<Object> rowColumns = new ArrayList();
            rowColumns.add(school.getName());
            rowColumns.add(this.studentRepository.countBySchool(school));
            rowColumns.add(DateUtils.format(school.getCreatedAt(), "dd/MM/yyyy HH:mm"));

            customReport.addTableRow(rowColumns);
        });

        customReport.closeTable();
        customReport.closeDocument();

        return customReport;
    }

}
