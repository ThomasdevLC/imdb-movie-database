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

/**
 * classe lit un fichier CSV contenant des noms de films liés à leurs acteurs  et les persiste en base
 * de données.
 */
public class MovieActorCsvReader {

    public static void extractMovieActors() {
    	
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

                MovieActor movieActor = new MovieActor(idMovie, idActor);

                em.persist(movieActor);

                System.out.println(" actor " + idActor + " movie " + idMovie);
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