package readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.CsvFileUtil;

/**
 * classe lit un fichier CSV contenant les noms de films et acteurs liés à leurs personnage/roles  et les persiste en base
 * de données.
 */

public class RoleCsvReader {
	  public static void extractRoles() {
	    	
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
	        EntityManager em = emf.createEntityManager();

	        String fileName = "roles.csv";

	        em.getTransaction().begin();

	        try {
	            Path path = CsvFileUtil.getPath(fileName);

	            List<String> allLines = Files.readAllLines(path);
            List<String> dataLines = allLines.subList(1, Math.min(5001, allLines.size())); 
//				List<String> dataLines = allLines.subList(1, allLines.size());

	            for (String line : dataLines) {
	                String[] col = line.split(";");

	                String idMovie = col[0].trim();
	                String idActor = col[1].trim();
	                String name = 	col.length > 2 ? col[2].trim() : null;

	                Role role = new Role(idMovie, idActor,name);

	                em.persist(role);

	                System.out.println( " character : " + name );
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