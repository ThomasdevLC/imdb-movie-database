package readers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.CsvFileUtil;
import utils.DatabaseUtil;
import utils.DateUtil;
import entities.Director;
import entities.Place;

public class DirectorCsvReader {

	public static void main(String[] args) {
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
				String city = placeDetails[0].trim();
				String state = (placeDetails.length > 1 && !placeDetails[1].isEmpty()) ? placeDetails[1].trim() : null;
				String countryName = (placeDetails.length > 2) ? placeDetails[2].trim() : null;

				Place birthPlace = DatabaseUtil.findOrCreatePlace(em, state, city, countryName);

				Director director = new Director(idDirector, name, birthDate, birthPlace, url);
				em.persist(director);

				System.out.println("Persisting director: " + director.getName());

			}

			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error : " + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}
	}

}
