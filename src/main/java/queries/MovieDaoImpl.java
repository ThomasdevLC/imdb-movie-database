package queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import entities.Actor;
import entities.Movie;
import java.util.List;

public class MovieDaoImpl implements MovieDao {

    private EntityManager entityManager;

    public MovieDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Movie> findMoviesByActorName(String actorName) {
        String dataBaseQuery = "SELECT m FROM Movie m JOIN m.actors a WHERE a.name = :actorName";
        TypedQuery<Movie> query = entityManager.createQuery(dataBaseQuery, Movie.class);
        query.setParameter("actorName", actorName.toLowerCase());
        return query.getResultList();
    }

    
    @Override
    public List<Actor> findActorsByMovieName(String movieName) {
        String dataBaseQuery = "SELECT a FROM Actor a JOIN a.movies m WHERE m.name = :movieName";
        TypedQuery<Actor> query = entityManager.createQuery(dataBaseQuery, Actor.class);
        query.setParameter("movieName", movieName.toLowerCase());
        return query.getResultList();
    }
    
    @Override
    public List<Movie> findMoviesBetweenTwoYears(int year1 , int year2) {
        String dataBaseQuery = "SELECT m FROM Movie m WHERE m.year BETWEEN :year1 AND :year2";
        TypedQuery<Movie> query = entityManager.createQuery(dataBaseQuery, Movie.class);
        query.setParameter("year1", year1);
        query.setParameter("year2", year2);
        return query.getResultList();
    }
    
    @Override
    public List<Movie> findCommonMoviesByActors(String actorName1, String actorName2) {
        String dataBaseQuery = "SELECT m FROM Movie m JOIN m.actors a1 JOIN m.actors a2 WHERE a1.name = :actorName1 AND a2.name = :actorName2";
        TypedQuery<Movie> query = entityManager.createQuery(dataBaseQuery, Movie.class);
        query.setParameter("actorName1", actorName1.toLowerCase());
        query.setParameter("actorName2", actorName2.toLowerCase());
        return query.getResultList();
    }
    
    @Override
    public List<Actor> findCommonActorsByMovies(String movieName1, String movieName2) {
        String dataBaseQuery = "SELECT  a FROM Actor a "
                    + "JOIN a.movies m1 JOIN a.movies m2 "
                    + "WHERE m1.name = :movieName1 AND m2.name = :movieName2";
        TypedQuery<Actor> query = entityManager.createQuery(dataBaseQuery, Actor.class);
        query.setParameter("movieName1", movieName1.toLowerCase());
        query.setParameter("movieName2", movieName2.toLowerCase());
        return query.getResultList();
    }

    @Override
    public List<Movie> findMoviesBetweenTwoYearsbyActor(int year1 , int year2,String actorName ) {
    	 String dataBaseQuery = "SELECT  m FROM Movie m "
                 + "JOIN m.actors a "
                 + "WHERE m.year BETWEEN :year1 AND :year2 "
                 + "AND a.name = :actorName";
        TypedQuery<Movie> query = entityManager.createQuery(dataBaseQuery, Movie.class);
        query.setParameter("year1", year1);
        query.setParameter("year2", year2);
        query.setParameter("actorName", actorName.toLowerCase());

        return query.getResultList();
    }
  
}
