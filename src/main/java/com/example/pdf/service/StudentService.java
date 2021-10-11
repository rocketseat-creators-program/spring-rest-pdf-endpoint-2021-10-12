package com.example.pdf.service;

import com.example.pdf.model.Student;
import com.example.pdf.repository.StudentRepository;
import com.example.pdf.util.DateUtils;
import com.example.pdf.util.ReportUtils;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Comparator;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public ByteArrayInputStream reportV1() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(baos));
        pdfDocument.setDefaultPageSize(PageSize.Default.rotate());

        Document document = new Document(pdfDocument);

        Text textTitle = new Text("Students List");
        textTitle.setFontSize(28);
        textTitle.setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD));

        Paragraph title = new Paragraph(textTitle);
        title.setTextAlignment(TextAlignment.CENTER);

        document.add(title);
        document.add(new Paragraph("\n"));

        Table table = new Table(5);
        table.useAllAvailableWidth();
        table.setTextAlignment(TextAlignment.CENTER);

        table.addHeaderCell("NAME");
        table.addHeaderCell("EMAIL");
        table.addHeaderCell("AGE");
        table.addHeaderCell("BIRTHDAY");
        table.addHeaderCell("CREATED AT");

        this.studentRepository.findAll().forEach(student -> {
            table.addCell(student.getName());
            table.addCell(student.getEmail());
            table.addCell(String.valueOf(DateUtils.age(student.getBirthday())));
            table.addCell(DateUtils.format(student.getBirthday(), "dd/MM/yyyy"));
            table.addCell(DateUtils.format(student.getCreatedAt(), "dd/MM/yyyy HH:mm"));
        });

        document.add(table);
        document.close();

        return new ByteArrayInputStream(baos.toByteArray());
    }

    public ByteArrayInputStream reportV2() throws IOException {
        ReportUtils report = ReportUtils.getInstance();
        report.setPageSize(PageSize.Default.rotate());

        report.addParagraph(new Paragraph("Students List")
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD))
        );

        report.addNewLine();
        report.openTable(5);
        report.addTableHeader("NAME", "EMAIL", "AGE", "BIRTHDAY", "CREATED AT");

        this.studentRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(student -> {
                    report.addTableColumn(student.getName());
                    report.addTableColumn(student.getEmail());
                    report.addTableColumn(DateUtils.age(student.getBirthday()));
                    report.addTableColumn(DateUtils.format(student.getBirthday(), "dd/MM/yyyy"));
                    report.addTableColumn(DateUtils.format(student.getCreatedAt(), "dd/MM/yyyy HH:mm"));
                });

        report.closeTable();
        report.closeDocument();

        return report.getByteArrayInputStream();
    }

}
