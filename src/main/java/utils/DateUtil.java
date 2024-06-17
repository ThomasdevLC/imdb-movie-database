package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DateUtil {

    // Define formatter for year-only, which defaults to January 1st
    private static final DateTimeFormatter YEAR_ONLY_FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendPattern("yyyy")
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                    .toFormatter(Locale.ENGLISH); // Locale doesn't matter for year-only

    // Define formatters for full dates in English and French
    private static final DateTimeFormatter FULL_DATE_FORMATTER_EN =
            DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.ENGLISH);

    private static final DateTimeFormatter FULL_DATE_FORMATTER_FR =
            DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);

    // Define formatter for month and day only, with default year 1900
    private static final DateTimeFormatter MONTH_DAY_FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendPattern("MMMM d")
                    .parseDefaulting(ChronoField.YEAR, 1900)
                    .toFormatter(Locale.ENGLISH);

    // Define formatter for month and year only, with default day 1
    private static final DateTimeFormatter MONTH_YEAR_FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendPattern("MMMM yyyy")
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                    .toFormatter(Locale.ENGLISH);

    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }

        // Remove any "c." prefix
        dateString = dateString.trim().replaceAll("^c\\.\\s*", "");

        if (dateString.matches("^\\d+$")) {
            try {
                // Attempt to parse as a year-only date
                int year = Integer.parseInt(dateString);
                // Assume January 1st of the given year
                return LocalDate.of(year, 1, 1);
            } catch (NumberFormatException e) {
                // Handle the case where dateString is not a valid number
                System.err.println("Invalid year format: " + dateString);
                return null;  // or throw an exception
            }
        }

        // Create a list of potential formatters
        List<DateTimeFormatter> formatters = new ArrayList<>();
        formatters.add(YEAR_ONLY_FORMATTER);
        formatters.add(FULL_DATE_FORMATTER_EN);
        formatters.add(FULL_DATE_FORMATTER_FR);
        formatters.add(MONTH_DAY_FORMATTER);
        formatters.add(MONTH_YEAR_FORMATTER);

        // Try each formatter until one succeeds
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Ignore and try the next formatter
            }
        }

        // If all parsing attempts fail, log an error or handle it
        System.err.println("Unable to parse date: " + dateString);
        return null;  // or throw an exception
    }
//
//    public static void main(String[] args) {
//        // Testing the parseDate method
//        System.out.println(parseDate("2000"));  // Parses as January 1, 2000
//        System.out.println(parseDate("January 1 2000"));  // Parses as January 1, 2000
//        System.out.println(parseDate("25 December 1990"));  // Parses as December 25, 1990
//        System.out.println(parseDate("1 October"));  // Parses as October 1, current year
//        System.out.println(parseDate("c. 1985"));  // Parses as 1985
//        System.out.println(parseDate("1"));  // Parses as January 1, current year
//    }
}


