package readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import entities.MovieActor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.CsvFileUtil;

public class MovieActorCsvReader {

    public static void main(String[] args) {
    	
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
        EntityManager em = emf.createEntityManager();

        String fileName = "castingPrincipal.csv";

        em.getTransaction().begin();

        try {
            Path path = CsvFileUtil.getPath(fileName);

            List<String> allLines = Files.readAllLines(path);
//            List<String> dataLines = allLines.subList(1, Math.min(51, allLines.size())); 
			List<String> dataLines = allLines.subList(1, allLines.size());

            for (String line : dataLines) {
                String[] col = line.split(";");

                String idMovie = col[0].trim();
                String idActor = col[1].trim();

                // Créer MovieActor avec les IDs
                MovieActor movieActor = new MovieActor(idMovie, idActor);

                // Persister MovieActor
                em.persist(movieActor);

                System.out.println(" actor " + idActor + " movie " + idMovie);
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