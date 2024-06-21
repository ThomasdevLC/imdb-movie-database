package readers;

import java.io.IOException;
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
import utils.PlaceCheckDatabaseUtil;
import utils.PlaceFormatterUtils;
import utils.DateUtil;
import utils.ActorHeightParserUtil;

/**
 * classe lit un fichier CSV contenant des noms d'acteurs et les persiste en base
 * de données.
 */

public class ActorCsvReader {
	
	public void extractActors() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
		EntityManager em = emf.createEntityManager();

		String fileName = "acteurs.csv";

		em.getTransaction().begin();

		try {
			Path path = CsvFileUtil.getPath(fileName);

			List<String> allLines = Files.readAllLines(path);
			List<String> dataLines = allLines.subList(1, Math.min(5001, allLines.size()));

			for (String line : dataLines) {
				String[] col = line.split(";");

				String idActor = col[0].trim();
				String name = col[1].trim();
				LocalDate birthDate = DateUtil.parseDate(col[2].trim());
				String placeInfo = col[3].trim();
				String heightStr = col[4].trim(); 
                double height = ActorHeightParserUtil.parseHeight(heightStr);
				String url = col[5].trim();

				  String[] placeDetails = placeInfo.split(",\\s*", 3);
			        String city = null;
			        String state = null;
			        String countryName = null;

			        if (placeDetails.length == 3) {
			            city = placeDetails[0].trim();
			            state = placeDetails[1].trim();
			            countryName = PlaceFormatterUtils.processCountryName(placeDetails[2].trim());
			        } else if (placeDetails.length == 2) {
			            city = placeDetails[0].trim();
			            countryName = PlaceFormatterUtils.processCountryName(placeDetails[1].trim());
			        }



				System.out.println("Pays: " + countryName);


				Place birthPlace = PlaceCheckDatabaseUtil.findOrCreatePlace(em, state, city, countryName);

				Actor actor = new Actor(idActor, name, birthDate, birthPlace,height, url);
				em.persist(actor);

				System.out.println(" actor: " + actor.getName());

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