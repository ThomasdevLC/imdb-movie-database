package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateUtil {

    public static LocalDate parseDate(String dateString) {
        if (dateString.isEmpty()) {
            return LocalDate.of(1900, 1, 1);
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.ENGLISH);
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date: " + e.getMessage());
                return LocalDate.of(1900, 1, 1);
            }
        }
    }
}