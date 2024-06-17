package queries;

import jakarta.persistence.*;
import entities.Movie;
import java.util.List;

public class MoviesByActorQueries {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
        EntityManager em = emf.createEntityManager();

        String actorName = "Fred Astaire"; // Nom de l'acteur

        try {
            // Requête JPQL pour obtenir les films pour un acteur spécifique via l'entité Actor
            String actorsQuery = "SELECT m FROM Movie m JOIN m.actors a WHERE a.name = :name";

            TypedQuery<Movie> query = em.createQuery(actorsQuery, Movie.class);
            query.setParameter("name", actorName);

            List<Movie> movies = query.getResultList();

            // Afficher les résultats
            System.out.println("Films pour l'acteur " + actorName + ":");
            for (Movie movie : movies) {
                System.out.println(movie.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
