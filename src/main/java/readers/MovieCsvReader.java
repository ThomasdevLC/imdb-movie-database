package readers;

import java.io.IOException;
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
import utils.MovieCheckDatabaseUtils;
import utils.MovieCountryUtil;
import utils.MovieRatingParserUtil;
import utils.MovieYearParserUtil;

/**
 * classe lit un fichier CSV contenant des noms de films et les persiste en base
 * de données.
 */
public class MovieCsvReader {

	public static void extractMovies() {
		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {
			emf = Persistence.createEntityManagerFactory("movie_database");
			em = emf.createEntityManager();

			String fileName = "films.csv";
			Path path = CsvFileUtil.getPath(fileName);

			List<String> allLines = Files.readAllLines(path);
			List<String> dataLines = allLines.subList(1, allLines.size());

			em.getTransaction().begin();

			for (String line : dataLines) {
				String[] col = line.split(";");

				String idMovie = col[0].trim();
				String name = col[1].trim();
                int year = MovieYearParserUtil.parseYear(col[2].trim()); 
				double rating = MovieRatingParserUtil.parseRating(col[3].trim());
				String url = col[4].trim();
				String shootingLocation = col[5].trim();
				String genreString = col[6].trim();
				String languageName = col[7].trim();
				String synopsis = col.length > 8 ? col[8].trim() : "";
				String countryName = col.length > 9 ? MovieCountryUtil.checkCountryFormat(col[9].trim()) : "";

				Country country = null;
				if (!countryName.isEmpty()) {
					country = MovieCheckDatabaseUtils.findOrCreateCountry(em, countryName);
				}

				Language language = MovieCheckDatabaseUtils.findOrCreateLanguage(em, languageName);

				Movie movie = new Movie(idMovie, name, year, rating, url, shootingLocation, language, synopsis, country);
				em.persist(movie);

				List<String> genreNames = Arrays.asList(genreString.split(","));
				for (String genreName : genreNames) {
					Genre genre = MovieCheckDatabaseUtils.findOrCreateGenre(em, genreName.trim());
					if (genre != null) {
						movie.getGenres().add(genre);
					}
				}

				System.out.println(" movie : " + movie.getName());
			}

			em.getTransaction().commit();

	       } catch (IOException e) {
	            e.printStackTrace();
	            em.getTransaction().rollback();
	            
	        } finally {
				em.close();
				emf.close();
	        }
	    }
	}
