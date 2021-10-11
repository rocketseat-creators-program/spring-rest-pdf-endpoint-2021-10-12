package com.example.pdf.service;

import com.example.pdf.repository.SchoolRepository;
import com.example.pdf.repository.StudentRepository;
import com.example.pdf.util.DateUtils;
import com.example.pdf.util.ReportUtils;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class SchoolService {

    private SchoolRepository schoolRepository;
    private StudentRepository studentRepository;

    public ByteArrayInputStream report() throws IOException {
        ReportUtils report = ReportUtils.getInstance();

        report.addParagraph(new Paragraph("Schools List")
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD))
        );

        report.addNewLine();
        report.openTable(3);
        report.addTableHeader("NAME", "STUDENTS", "CREATED AT");

        int totalStudents = this.schoolRepository.findAll().stream().map(school -> {
            int studentsCount = this.studentRepository.countBySchool(school);

            report.addTableColumn(school.getName());
            report.addTableColumn(studentsCount);
            report.addTableColumn(DateUtils.format(school.getCreatedAt(), "dd/MM/yyyy HH:mm"));

            return studentsCount;
        }).mapToInt(Integer::valueOf).sum();

        report.addTableFooter(null, totalStudents, null);

        report.closeTable();
        report.closeDocument();

        return report.getByteArrayInputStream();
    }

}
