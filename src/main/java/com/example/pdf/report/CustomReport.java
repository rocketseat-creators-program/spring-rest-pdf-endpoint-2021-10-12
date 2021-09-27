package com.example.pdf.report;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class CustomReport {

    private Table table;
    private PageSize pageSize;
    private Document document;
    private PdfDocument pdfDocument;
    private ByteArrayOutputStream baos;

    public CustomReport() {
        this.baos = new ByteArrayOutputStream();
        this.pdfDocument = new PdfDocument(new PdfWriter(this.baos));
        this.document = new Document(this.pdfDocument);
    }

    public void pageSize(PageSize pageSize) {
        this.pageSize = pageSize;
        this.pdfDocument.setDefaultPageSize(pageSize);
    }

    public void landscape() {
        this.pdfDocument.setDefaultPageSize(ObjectUtils.isEmpty(this.pageSize) ? PageSize.Default.rotate() : this.pageSize.rotate());
    }

    public void addNewLine() {
        this.document.add(new Paragraph("\n"));
    }

    public void addParagraph(String text) {
        CustomText customText = CustomText.builder().value(text).build();
        this.addParagraph(customText);
    }

    public void addParagraph(CustomText text) {
        this.document.add(this.defaultParagraph(text));
    }

    public void closeDocument() {
        this.document.close();
    }

    public void openTable(Integer columnWidth) {
        this.table = new Table(columnWidth);
        this.table.useAllAvailableWidth();
        this.table.setTextAlignment(TextAlignment.CENTER);
    }

    public void addTableHeader(CustomText header) {
        this.validateTable();
        this.table.addHeaderCell(this.defaultParagraph(header));
    }

    public void addTableHeader(String header) {
        CustomText customText = CustomText.builder().value(header).build();
        this.addTableHeader(customText);
    }

    public void addTableHeaders(CustomText... headers) {
        for (CustomText customText : headers) {
            this.addTableHeader(customText);
        }
    }

    public void addTableHeaders(String... headers) {
        for (String header : headers) {
            this.addTableHeader(header);
        }
    }

    public void addTableRow(List<Object> columns) {
        this.validateTable();
        for (Object column : columns) {
            this.table.addCell(column.toString());
        }
    }

    public void closeTable() {
        this.validateTable();
        this.document.add(this.table);
    }

    public ByteArrayInputStream getByteArrayInputStream() {
        return new ByteArrayInputStream(this.baos.toByteArray());
    }

    private Paragraph defaultParagraph(CustomText customText) {
        Paragraph paragraph = new Paragraph();
        Text text = new Text(customText.getValue());

        if (!ObjectUtils.isEmpty(customText.getColor()))
            text.setFontColor(customText.getColor());

        if (!ObjectUtils.isEmpty(customText.getFontSize()))
            text.setFontSize(customText.getFontSize());

        if (!ObjectUtils.isEmpty(customText.getFontFamily()))
            text.setFontFamily(customText.getFontFamily());

        if (!ObjectUtils.isEmpty(customText.getTextAlignment()))
            paragraph.setTextAlignment(customText.getTextAlignment());

        if (!ObjectUtils.isEmpty(customText.getFont())) {
            try {
                PdfFont font = PdfFontFactory.createFont(customText.getFont());
                text.setFont(font);
            } catch (IOException e) {

            }
        }

        paragraph.add(text);
        return paragraph;
    }

    private void validateTable() {
        if (ObjectUtils.isEmpty(this.table)) {
            throw new RuntimeException("PDF Table cannot be null.");
        }
    }

}
