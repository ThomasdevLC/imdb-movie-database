package queries;

import jakarta.persistence.*;
import java.util.List;

public class ActorsByMovieQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
        EntityManager em = emf.createEntityManager();

        String movieName = "Le fantôme de Milburn"; 
        try {
            String jpql = "SELECT a.name FROM Actor a JOIN a.movies m WHERE m.name = :movieName";

            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("movieName", movieName.toLowerCase());

            List<String> actorNames = query.getResultList();

            // Afficher les résultats
            System.out.println("Acteurs pour le film '" + movieName + "':");
            for (String actorName : actorNames) {
                System.out.println(actorName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
