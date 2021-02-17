package com.hsbc.lms.util;

import java.time.LocalDate;

public class CalculateDueDate {

    public static LocalDate calculateDueDate(LocalDate localDate){
        return localDate.plusDays(15);
    }

}
