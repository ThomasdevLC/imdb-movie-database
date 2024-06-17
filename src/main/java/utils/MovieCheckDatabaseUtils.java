package utils;
import jakarta.persistence.EntityManager;
import entities.Country;
import entities.Genre;
import entities.Language;
public class MovieCheckDatabaseUtils {

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
