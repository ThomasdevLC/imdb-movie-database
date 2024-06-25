package utils;
import jakarta.persistence.EntityManager;
import entities.Country;
import entities.Genre;
import entities.Language;

/**
 * Classe utilitaire pour la gestion des recherches ou créations d'entités Language, Country et Genre
 * dans la table de données des films.
 * 
 * Cette classe fournit des méthodes  pour rechercher ou créer des entités basées sur leurs noms.
 * Utilisée dans la classe {@link MovieCsvReader} pour gérer les données raltives à la langue, le genre et le pays d'origine.
 */


public class MovieCheckDatabaseUtils {

	 /**
     * Recherche ou crée une entité Language dans la base de données en fonction du nom de la langue en parametre.
     *
     * @param em   Gestionnaire d'entités EntityManager pour la persistance .
     * @param languageName Le nom de la langue .
     * @return L'entité Language qui existe déjà ou nouvellement créée .
     */
	
	public static Language findOrCreateLanguage(EntityManager em, String languageName) {
        Language language = em.createQuery("SELECT l FROM Language l WHERE l.name = :name", Language.class)
                .setParameter("name", languageName)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (language == null) {
            language = new Language(languageName);
            em.persist(language);
        }

        return language;
    }
	
	/**
     * Recherche ou crée une entité Country dans la base de données en fonction du nom du pays (countryName).
     *
     * @param em          Le gestionnaire d'entités EntityManager pour la persistance.
     * @param countryName Le nom du pays.
     * @return L'entité Country  qui existe déjà ou nouvellement créée.
     */

    public static Country findOrCreateCountry(EntityManager em, String countryName) {
        Country country = em.createQuery("SELECT c FROM Country c WHERE c.name = :name", Country.class)
                .setParameter("name", countryName)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (country == null) {
            country = new Country(countryName);
            em.persist(country);
        }

        return country;
    }
    
    /**
     * Recherche ou crée une entité Genre dans la table de données en fonction du nom du genre (genreName).
     *
     * @param em        Le gestionnaire EntityManager pour la persistance.
     * @param genreName Le nom du genre.
     * @return L'entité Genre qui existe déjà ou nouvellement créée .
     */

    public static Genre findOrCreateGenre(EntityManager em, String genreName) {
        Genre genre = em.createQuery("SELECT g FROM Genre g WHERE g.name = :name", Genre.class)
                .setParameter("name", genreName)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (genre == null) {
            genre = new Genre(genreName);
            em.persist(genre);
        }

        return genre;
    }
}
