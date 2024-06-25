package utils;

/**
 * Classe utilitaire pour l'analyse des années de sortie des films.
 *
 * Classe fournit une méthode  pour extraire une année à partir de chaînes de caracteres,
 * Utilisée dans la classe {@link MovieCsvReader} pour le traitement des années de sortie des films.

 */
public class MovieYearParserUtil {

    /**
     * Méthode sépare la chaîne en morceau (parts) et convertit en entier le dernier morceau(parts.length - 1).
     *
     * @param yearStr l'année de type String.
     * @return L'année sous forme d'entier Integer.
     * 
     */
    public static int parseYear(String yearStr) {
        String[] parts = yearStr.split("\\D+");
        
        return Integer.parseInt(parts[parts.length - 1]);
    }
}
