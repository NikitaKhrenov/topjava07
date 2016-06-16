package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static final DateTimeFormatter DATE_TME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetween(LocalDateTime ldt, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        if  (startDate == null)
            startDate = LocalDate.MIN;
        if (startTime == null)
            startTime = LocalTime.MIN;
        if (endDate == null)
            endDate = LocalDate.MAX;
        if (endTime == null)
            endTime = LocalTime.MAX;
        return (ldt.toLocalDate().compareTo(startDate) >= 0 && ldt.toLocalDate().compareTo(endDate) <= 0)
                && (ldt.toLocalTime().compareTo(startTime) >= 0 && ldt.toLocalTime().compareTo(endTime) <= 0);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TME_FORMATTER);
    }
}
