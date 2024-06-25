package utils;

/**
 * Classe utilitaire pour le traitement des tailles des acteurs.
 * Classe permet de convertir une chaîne représentant une taille en mètres en un nombre de type double.
 * Utilisé dans la classe {@link ActorCsvReader} pour extraire et traiter les données des acteurs.
 */

public class ActorHeightParserUtil {

	/**
	 * Converti et formate la taille (height) de string à double. 
	 *
	 * @param heightStr taille de l'acteur à convertir. 
	 * 
	 * 		Les caractères d'espacement sont remplacés par des espaces normaux.
	 *      Les virgules sont converties en points
	 *      Les caractères non numériques et non des points sont supprimés.
	 *                  
	 * @return La taille convertie en type double , ou 0.0 si la conversion a
	 *         echouée.
	 */
	public static double parseHeight(String heightStr) {
		if (heightStr == null || heightStr.trim().isEmpty()) {
			return 0.0;
		}

		heightStr = heightStr.replace("\u202F", " ");
		heightStr = heightStr.replace(",", ".");
		heightStr = heightStr.replaceAll("[^\\d.]", "");

		if (!heightStr.isEmpty()) {
			try {
				return Double.parseDouble(heightStr);
			} catch (NumberFormatException e) {
				System.err.println("Unable to parse height: " + heightStr);
			}
		}

		System.err.println("Invalid height format: " + heightStr);
		return 0.0;
	}
}
