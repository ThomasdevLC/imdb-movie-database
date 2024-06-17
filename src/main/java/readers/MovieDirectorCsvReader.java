package readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import entities.Director;
import entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.CsvFileUtil;

public class MovieDirectorCsvReader {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
		EntityManager em = emf.createEntityManager();

		String fileName = "film_realisateurs.csv";

		em.getTransaction().begin();

		try {
			Path path = CsvFileUtil.getPath(fileName);

			List<String> allLines = Files.readAllLines(path);
//			List<String> dataLines = allLines.subList(1, Math.min(51, allLines.size()));
			List<String> dataLines = allLines.subList(1, allLines.size());

			for (String line : dataLines) {
				String[] col = line.split(";");

				String idMovie = col[0].trim();
				String idDirector = col[1].trim();

				// Chercher le film par son idMovie
				Movie movie = em.createQuery("SELECT m FROM Movie m WHERE m.idMovie = :idMovie", Movie.class)
						.setParameter("idMovie", idMovie).getResultList().stream().findFirst().orElse(null);

				// Chercher le réalisateur par son idDirector
				  Director director = em.createQuery("SELECT d FROM Director d WHERE d.idDirector = :idDirector", Director.class)
                          .setParameter("idDirector", idDirector)
                          .getResultList()
                          .stream()
                          .findFirst()
                          .orElse(null);
				  
				if (movie != null && director != null) {
					// Associer le réalisateur au film
					movie.setDirector(director);
					// Mettre à jour l'entité Movie pour persister l'association
					em.merge(movie);
				} else {
					System.out.println(" idMovie: " + idMovie
							+ ", idDirector: " + idDirector);
				}
			}

			// Commit de la transaction
			em.getTransaction().commit();

		} catch (IOException e) {
			e.printStackTrace();
			// Rollback en cas d'erreur
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			// Fermeture des ressources
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}
}
