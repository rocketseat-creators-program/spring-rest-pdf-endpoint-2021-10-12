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
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class CustomReport {

    private Table table;
    private Document document;
    private PdfDocument pdfDocument;
    private ByteArrayOutputStream baos;

    private CustomReport() {
        this.baos = new ByteArrayOutputStream();
        this.pdfDocument = new PdfDocument(new PdfWriter(this.baos));
        this.document = new Document(this.pdfDocument);
    }

    public static CustomReport builder() {
        return new CustomReport();
    }

    public CustomReport landscape(PageSize pageSize) {
        this.pdfDocument.setDefaultPageSize(pageSize.rotate());
        return this;
    }

    public CustomReport addNewLine() {
        this.document.add(new Paragraph("\n"));
        return this;
    }

    public CustomReport addParagraph(String text) {
        CustomText customText = CustomText.builder().value(text).build();
        return this.addParagraph(customText);
    }

    public CustomReport addParagraph(CustomText text) {
        this.document.add(this.defaultParagraph(text));
        return this;
    }

    public ByteArrayInputStream build() {
        this.document.close();
        return new ByteArrayInputStream(this.baos.toByteArray());
    }

    public CustomReport openTable(Integer columnWidth) {
        this.table = new Table(columnWidth);
        this.table.useAllAvailableWidth();
        this.table.setTextAlignment(TextAlignment.CENTER);

        return this;
    }

    public CustomReport addTableHeader(CustomText header) {
        this.validateTable();
        this.table.addHeaderCell(this.defaultParagraph(header));
        return this;
    }

    public CustomReport addTableHeader(String header) {
        CustomText customText = CustomText.builder().value(header).build();
        return this.addTableHeader(customText);
    }

    public CustomReport addTableHeaders(CustomText... headers) {
        for (CustomText customText : headers) {
            this.addTableHeader(customText);
        }

        return this;
    }

    public CustomReport addTableHeaders(String... headers) {
        for (String header : headers) {
            this.addTableHeader(header);
        }

        return this;
    }

    public CustomReport addTableRow(Object[] columns) {
        this.validateTable();
        for (int i = 0; i < columns.length; i++) {
            this.table.addCell(columns[i].toString());
        }

        return this;
    }

    public CustomReport addTableRows(List<Object[]> rows) {
        for (Object[] objects : rows) {
            this.addTableRow(objects);
        }

        return this;
    }

    public CustomReport closeTable() {
        this.validateTable();
        this.document.add(this.table);
        return this;
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
        paragraph.setVerticalAlignment(VerticalAlignment.MIDDLE);
        paragraph.setHorizontalAlignment(HorizontalAlignment.CENTER);

        return paragraph;
    }

    private void validateTable() {
        if (ObjectUtils.isEmpty(this.table)) {
            throw new RuntimeException("PDF Table cannot be null.");
        }
    }

}
