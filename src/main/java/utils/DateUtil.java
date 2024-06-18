package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Classe utilitaire pour le traitement des dates de naissance des acteurs et réalisateurs.
 * classe founrnit des méthodes pour parser des chaînes de date et les convertir en objets LocalDate.
 * Utilisé dans la classe {@link ActorCsvReader} {@link DirectorCsvReader} .
 */

public class DateUtil {

    private static final DateTimeFormatter YEAR_ONLY_FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendPattern("yyyy")
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                    .toFormatter(Locale.ENGLISH); 

    private static final DateTimeFormatter FULL_DATE_FORMATTER_EN =
            DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.ENGLISH);

    private static final DateTimeFormatter FULL_DATE_FORMATTER_FR =
            DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);

    private static final DateTimeFormatter MONTH_DAY_FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendPattern("MMMM d")
                    .parseDefaulting(ChronoField.YEAR, 1900)
                    .toFormatter(Locale.ENGLISH);

    private static final DateTimeFormatter MONTH_YEAR_FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendPattern("MMMM yyyy")
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                    .toFormatter(Locale.ENGLISH);
    
    
    /**
     * Parse chaîne de date en  objet LocalDate.
     *
     * La méthode tente de parser la dateString donnée en utilisant divers formats de date :
 
     * 		Format d'année seule (par exemple, "1990") avec défaut au 1er janvier.
     * 		Formats de date complète en anglais (par exemple, "June 15 1990") et en français (par exemple, "15 juin 1990").
     * 		Format mois et jour seulement (par exemple, "June 15") avec année par défaut 1900.
     * 		Format mois et année seulement (par exemple, "June 1990") avec jour par défaut 1.
     *
     * Si dateString n'est pas une date valide, alors message d'erreur et retourne  null.
     *
     * @param dateString La chaîne de date à parser.
     * @return La date parsée en tant qu'objet LocalDate, ou null si le parsing échoue.
     */

    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }

        // Supprime les  "c."
        dateString = dateString.trim().replaceAll("^c\\.\\s*", "");

        if (dateString.matches("^\\d+$")) {
            try {
                int year = Integer.parseInt(dateString);
                return LocalDate.of(year, 1, 1);
            } catch (NumberFormatException e) {
                System.err.println("Invalid year format: " + dateString);
                return null;  
            }
        }

        List<DateTimeFormatter> formatters = new ArrayList<>();
        formatters.add(YEAR_ONLY_FORMATTER);
        formatters.add(FULL_DATE_FORMATTER_EN);
        formatters.add(FULL_DATE_FORMATTER_FR);
        formatters.add(MONTH_DAY_FORMATTER);
        formatters.add(MONTH_YEAR_FORMATTER);

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
            }
        }

        System.err.println("Unable to parse date: " + dateString);
        return null;  
    }

}


