package com.example.pdf.report;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.property.TextAlignment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomText {

    private Color color;
    private String font;
    private String value;
    private Float fontSize;
    private List<String> fontFamily;
    private TextAlignment textAlignment;

}
