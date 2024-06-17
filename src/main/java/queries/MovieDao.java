package queries;

import entities.Actor;
import entities.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> findMoviesByActorName(String actorName);
    List<Actor> findActorsByMovieName(String movieName);
    List<Movie> findMoviesBetweenTwoYears(int year1 , int year2);
    List<Movie> findCommonMoviesByActors(String actorName1, String actorName2);
    List<Actor> findCommonActorsByMovies(String movieName1, String movieName2);
    List<Movie> findMoviesBetweenTwoYearsbyActor(int year1 , int year2,String actorName );
}