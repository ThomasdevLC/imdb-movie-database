package researchApp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import queries.MovieDao;
import queries.MovieDaoImpl;
import entities.Actor;
import entities.Movie;
import java.util.List;

public class MainApplication {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
		EntityManager em = emf.createEntityManager();

		MovieDao movieDAO = new MovieDaoImpl(em);
		

		String actorName = "Fred Astaire";
		List<Movie> moviesForActor = movieDAO.findMoviesByActorName(actorName);
		System.out.println("Films pour l'acteur " + actorName + ":");
		for (Movie movie : moviesForActor) {
			System.out.println(movie.getName());
		}

		
		String movieName = "Le fantôme de Milburn";
		List<Actor> actorsForMovie = movieDAO.findActorsByMovieName(movieName);
		System.out.println("Acteurs pour le film '" + movieName + "':");
		for (Actor actor : actorsForMovie) {
			System.out.println(actor.getName());
		}
		
		int year1 = 1985;
		int year2 = 1990;

		List<Movie> releasedMovies = movieDAO.findMoviesBetweenTwoYears(year1, year2);
		System.out.println("Films réalisés entre " + year1 + " et " + year2 + ":");
		for (Movie movie : releasedMovies) {
			System.out.println(movie.getName());
		}
		

		String actorName1 = "Craig Wasson";
		String actorName2 = "Alice Krige";

		List<Movie> commonMovies = movieDAO.findCommonMoviesByActors(actorName1, actorName2);
		System.out.println("Films communs à " + actorName1 + " et " + actorName2 + ":");
		for (Movie movie : commonMovies) {
			System.out.println(movie.getName());
		}
		
		String movieName1 = "Brigitte Bardot: Mister Sun";
		String movieName2 = "Brigitte Bardot: Bubble Gum";

		List<Actor> commonActors = movieDAO.findCommonActorsByMovies(movieName1, movieName2);
		System.out.println("Acteurs communs à " + movieName1 + " et " + movieName2 + ":");
		for (Actor actor : commonActors) {
		    System.out.println(actor.getName());
		}
		
		int yearOne = 1960;
		int yearTwo = 1990;
		String actor = "Craig Wasson";

		List<Movie> releasedMoviesByperiodByactor = movieDAO.findMoviesBetweenTwoYearsbyActor(yearOne, yearTwo,actor );
		System.out.println("Films réalisés entre " + yearOne + " et " + yearTwo + " avec "+ actor+ ":");
		for (Movie movie : releasedMoviesByperiodByactor) {
			System.out.println(movie.getName() +" "+ movie.getYear());
		}
		

		em.close();
		emf.close();
	}
}
