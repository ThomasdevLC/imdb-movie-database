package readers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import entities.Country;
import entities.Genre;
import entities.Language;
import entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.CsvFileUtil;


public class MovieCsvReader {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("movie_database");
            em = emf.createEntityManager();

            String fileName = "films.csv";
            Path path = CsvFileUtil.getPath(fileName);

            List<String> allLines = Files.readAllLines(path);
            List<String> dataLines = allLines.subList(1, Math.min(51, allLines.size()));

            em.getTransaction().begin();

            for (String line : dataLines) {
                String[] col = line.split(";");

                String idMovie = col[0].trim();
                String name = col[1].trim();
                int year = parseYear(col[2].trim());
                double rating = col[3].trim().isEmpty() ? 0.0 : Double.parseDouble(col[3].trim());
                String url = col[4].trim();
                String genreString = col[6].trim(); 
                String languageName = col[7].trim();
                String synopsis = col[8].trim();
                String countryName = col.length > 9 ? col[9].trim() : "";

                Country country = null;
                if (!countryName.isEmpty()) {
                    country = findOrCreateCountry(em, countryName);
                }

                Language language = findOrCreateLanguage(em, languageName);
                
                Movie movie = new Movie(idMovie, name, year, rating, url, language, synopsis, country);
                em.persist(movie);

                List<String> genreNames = Arrays.asList(genreString.split(","));
                for (String genreName : genreNames) {
                    Genre genre = findOrCreateGenre(em, genreName.trim());
                    if (genre != null) {
                        movie.getGenres().add(genre);
                    }
                }

             
                System.out.println("Persisting movie: " + movie.getName());
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

    private static int parseYear(String yearStr) {
        String[] parts = yearStr.split("\\D+");
        return Integer.parseInt(parts[parts.length - 1]);
    }

    private static Language findOrCreateLanguage(EntityManager em, String languageName) {
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

    private static Country findOrCreateCountry(EntityManager em, String countryName) {
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

    private static Genre findOrCreateGenre(EntityManager em, String genreName) {
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


