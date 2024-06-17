package readers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import entities.Actor;
import entities.Place;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.CsvFileUtil;
import utils.DatabaseUtil;
import utils.DateUtil;

public class ActorCsvReader {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
		EntityManager em = emf.createEntityManager();

		String fileName = "acteurs.csv";

		em.getTransaction().begin();

		try {
			Path path = CsvFileUtil.getPath(fileName);

			List<String> allLines = Files.readAllLines(path);
			List<String> dataLines = allLines.subList(1, Math.min(51, allLines.size()));

			for (String line : dataLines) {
				String[] col = line.split(";");

				String idActor = col[0].trim();
				String name = col[1].trim();
				LocalDate birthDate = DateUtil.parseDate(col[2].trim());
				String placeInfo = col[3].trim();
				String heightStr = col[4].trim(); 
				double height;
				    if (heightStr.isEmpty()) {
				        height = 0.0; 
				    } else {
				        String[] parts = heightStr.split("\\s+");  
				        height = Double.parseDouble(parts[0]);  
				    }
				String url = col[5].trim();

				String[] placeDetails = placeInfo.split(",\\s*", 3);
				String city = placeDetails[0].trim();
				String state = (placeDetails.length > 1 && !placeDetails[1].isEmpty()) ? placeDetails[1].trim() : null;
				String countryName = (placeDetails.length > 2) ? placeDetails[2].trim() : null;

				Place birthPlace = DatabaseUtil.findOrCreatePlace(em, state, city, countryName);

				Actor actor = new Actor(idActor, name, birthDate, birthPlace,height, url);
				em.persist(actor);

				System.out.println("Persisting actor: " + actor.getName());

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