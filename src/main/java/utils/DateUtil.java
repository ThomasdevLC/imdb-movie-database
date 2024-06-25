package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Classe utilitaire pour le traitement des dates de naissance des acteurs et réalisateurs.
 * classe founrnit des méthodes pour parser des chaînes de date et les convertir en objets LocalDate.
 * Utilisé dans la classe {@link ActorCsvReader} {@link DirectorCsvReader} .
 */

public class DateUtil {

    // Formateurs pour les différents formats de date
    private static final DateTimeFormatter FULL_DATE_FORMATTER_EN = DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter FULL_DATE_FORMATTER_FR = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
    private static final DateTimeFormatter MONTH_YEAR_FORMATTER_EN = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);

    /**
     * Parse la chaîne de date en objet LocalDate.
     *
     * @param dateString  chaîne de date à parser.
     * @return date au format LocalDate, ou null si le parsing échoue.
     */
    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }

        // Supprime les "c."
        dateString = dateString.trim().replaceAll("^c\\.\\s*", "");

        try {
            //  année seule (ex : "1990")
            if (dateString.matches("^\\d{4}$")) {
                int year = Integer.parseInt(dateString);
                return LocalDate.of(year, 1, 1);
            }

            //  mois et année seulement (ex : "September 1935")
            if (dateString.matches("^[A-Za-z]+ \\d{4}$")) {
                YearMonth ym = YearMonth.parse(dateString, MONTH_YEAR_FORMATTER_EN);
                return ym.atDay(1);
            }

            //  date complète en anglais (ex : "September 16 1935")
            if (dateString.matches("^[A-Za-z]+ \\d{1,2} \\d{4}$")) {
                return LocalDate.parse(dateString, FULL_DATE_FORMATTER_EN);
            }

            //  date complète en français (ex : "16 septembre 1935")
            if (dateString.matches("^\\d{1,2} [A-Za-zéêèàûîô]+ \\d{4}$")) {
                return LocalDate.parse(dateString, FULL_DATE_FORMATTER_FR);
            }
            
            // Mmois et jour (ex : "July 10") ou chiffre seul
            if (dateString.matches("^[A-Za-z]+ \\d{1,2}$") || dateString.matches("^\\d$")) {
                return null;
            }

            // Aucun format reconnu
            System.err.println("Unrecognized date format : " + dateString);
            return null;

        } catch (Exception e) {
            System.err.println("Error parsing date : " + dateString);
            e.printStackTrace();
            return null;
        }
    }
}


