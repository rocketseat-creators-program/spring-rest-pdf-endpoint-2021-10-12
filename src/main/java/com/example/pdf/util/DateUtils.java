package com.example.pdf.util;

import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.Period;

public class DateUtils {

    public static int age(LocalDate birthday) {
        if (!ObjectUtils.isEmpty(birthday)) {
            return Period.between(birthday, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

}
