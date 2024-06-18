package readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.CsvFileUtil;
import utils.DirectorCheckDatabaseUtil;
import utils.DateUtil;
import entities.Director;
import entities.Place;
/**
 * classe lit un fichier CSV contenant des noms de réalisateurs et les persiste en base
 * de données.
 */
public class DirectorCsvReader {

	public  void extractDirectors() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
		EntityManager em = emf.createEntityManager();

		String fileName = "realisateurs.csv";

		em.getTransaction().begin();

		try {
			Path path = CsvFileUtil.getPath(fileName);

			List<String> allLines = Files.readAllLines(path);
//			List<String> dataLines = allLines.subList(1, Math.min(51, allLines.size()));
			List<String> dataLines = allLines.subList(1, allLines.size());

			for (String line : dataLines) {
				String[] col = line.split(";");

				String idDirector = col[0].trim();
				String name = col[1].trim();
				LocalDate birthDate = DateUtil.parseDate(col[2].trim());
				String placeInfo = col[3].trim();
				String url = col[4].trim();

				String[] placeDetails = placeInfo.split(",\\s*", 3);
				
				String city = null;
				String state = null;
				String countryName = null;

				if (placeDetails.length == 3) {
				    city = placeDetails[0].trim();
				    state = placeDetails[1].trim();
				    countryName = placeDetails[2].trim();
				}

				if (placeDetails.length == 2) {
				    city = placeDetails[0].trim();
				    countryName = placeDetails[1].trim();
				}


				Place birthPlace = DirectorCheckDatabaseUtil.findOrCreatePlace(em, city, state, countryName);

				Director director = new Director(idDirector, name, birthDate, birthPlace, url);
				em.persist(director);

				System.out.println(" director: " + director.getName() );

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
