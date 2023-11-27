package com.example.CostControl.Util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateTimeFormatter {

    private final static String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";

    public Date formatStringToDate(String dateTime) throws ParseException {
        return new SimpleDateFormat(DATE_TIME_FORMAT).parse(dateTime);
    }
}
