package utils;
/**
 * Classe utilitairepour formater des noms de pays.
 * Utilisé dans la classe {@link DirectorCsvReader} {@link ActorCsvReader} pour gérer les données relatives au lieu de naissance.

 */
public class PlaceFormatterUtils {
    /**

	* @param countryPart le texte contenant le nom du pays à traiter
    * @return le nom actuel du pays en enlevant "now" si présent
    */
	   public static String processCountryName(String countryPart) {
	        if (countryPart.contains("now")) {
	            String[] parts = countryPart.split("now")[1].trim().split(",");
	            return parts[parts.length - 1].trim().replaceAll("\\[|\\]", "");
	        }
	        return countryPart;
	    }

}
