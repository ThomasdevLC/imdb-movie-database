package utils;

/**
 * Classe utilitaire pour le traitement des noms de pays d'origine des films.
 * 
 * Cette classe fournit une méthode pour s'assurrer que les noms de pays respectent un certain format (non null et max 15 caractères) 
 * 
 * Utilisée dans la classe {@link MovieCsvReader} pour le traitement des noms de pays.
 * 

 */

public class MovieCountryUtil {

	public static String checkCountryFormat(String str) {
        if (str == null || str.trim().isEmpty() || str.trim().length() > 15) {
            return "";
        } else {
            return str.trim();
        }
    }
}
