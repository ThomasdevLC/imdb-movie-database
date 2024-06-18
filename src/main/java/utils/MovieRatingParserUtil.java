package utils;

/**
 *  Classe utilitaire pour anlyser une chaîne de caractères représentant une note pour la convertir en type double.
  * Utilisée dans la classe {@link MovieCsvReader} pour le traitement des noms des notes des films.

 */

public class MovieRatingParserUtil {

	   /**
     *
     * Cette méthode analyse la chaîne de caractères ratingStr qui représente une note
     * pour la convertir en un nombre décimal de type double. 
     *  Les virgules sont converties en points
     *
     * @param ratingStr La chaîne de caractères = la note .
     * @return La note convertie en double. Si l'analyse échoue, elle renvoie 0.0.
     */
	public static double parseRating(String ratingStr) {
		if (ratingStr == null || ratingStr.trim().isEmpty()) {
			return 0.0;
		}

		String normalizedRatingStr = ratingStr.replace(',', '.');

		try {
			return Double.parseDouble(normalizedRatingStr);
		} catch (NumberFormatException e) {
			System.err.println("Unable to parse rating: " + ratingStr);
			return 0.0;
		}
	}
}
