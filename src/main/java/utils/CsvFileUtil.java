package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;

public class CsvFileUtil {

    /**
     * Obtenir le chemin d'un fichier CSV .
     * @param fileName Le nom du fichier CSV .
     * @return Le chemin du fichier sous forme de Path.
     * @throws RuntimeException Si le fichier n'est pas trouvé ou que le chemin est invalide.
     */
    public static Path getPath(String fileName) {
        try {
            URL resourceUrl = CsvFileUtil.class.getClassLoader().getResource(fileName);
            if (resourceUrl != null) {
                return Paths.get(resourceUrl.toURI());
            } else {
                throw new IllegalArgumentException("Fichier " + fileName + " introuvable dans les ressources.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du chemin du fichier : " + fileName, e);
        }
    }

    /**
     * Vérifier si un fichier CSV existe dans les ressources.
     * @param fileName Le nom du fichier CSV.
     * @return true si le fichier existe, false sinon.
     */
    public static boolean fileExists(String fileName) {
        try {
            return Files.exists(getPath(fileName));
        } catch (Exception e) {
            return false;
        }
    }
}
