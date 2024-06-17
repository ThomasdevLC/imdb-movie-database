//package queries;
//
//import jakarta.persistence.*;
//import entities.Movie;
//import java.util.List;
//
//public class CommonMoviesByActorsQuery {
//
//	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
//		EntityManager em = emf.createEntityManager();
//
//		String actorName1 = "Craig Wasson";
//		String actorName2 = "Alice Krige";
//
//		try {
//			// Requête JPQL pour obtenir les films communs à deux acteurs
//			String jpql = "SELECT m FROM Movie m " + "JOIN m.actors a1 " + "JOIN m.actors a2 "
//					+ "WHERE a1.name = :actorName1 " + "AND a2.name = :actorName2";
//
//			TypedQuery<Movie> query = em.createQuery(jpql, Movie.class);
//			query.setParameter("actorName1", actorName1.toLowerCase());
//			query.setParameter("actorName2", actorName2.toLowerCase());
//
//			List<Movie> movies = query.getResultList();
//
//			// Afficher les résultats
//			System.out.println("Films communs à " + actorName1 + " et " + actorName2 + ":");
//			for (Movie movie : movies) {
//				System.out.println(movie.getName());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			em.close();
//			emf.close();
//		}
//	}
//}
