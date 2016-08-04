package ru.javawebinar.topjava.util.converter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Nikita on 01.08.2016.
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    @Override
    public LocalDateTime parse(String s, Locale locale) throws ParseException {
        return LocalDateTime.parse(s);
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
