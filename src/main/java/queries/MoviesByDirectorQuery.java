package queries;

import java.util.List;

import entities.Director;
import entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class MoviesByDirectorQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
        EntityManager em = emf.createEntityManager();

        String directorName = "John Irvin"; // Exemple de nom de réalisateur

        try {
            // Requête JPQL pour obtenir les films par nom de réalisateur
            String jpql = "SELECT m FROM Movie m JOIN m.director d WHERE d.name = :name";
            TypedQuery<Movie> query = em.createQuery(jpql, Movie.class);
            query.setParameter("name", directorName);

            List<Movie> movies = query.getResultList();

            // Afficher les résultats
            System.out.println("Films réalisés par " + directorName + ":");
            for (Movie movie : movies) {
                System.out.println(movie.getName() + " (" + movie.getYear() + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
