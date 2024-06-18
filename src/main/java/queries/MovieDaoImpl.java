package queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import entities.Actor;
import entities.Movie;
import java.util.List;


/**
 * Implémentation de l'interface MovieDao fournissant les méthodes pour accéder aux données dans la base de données.
 */
public class MovieDaoImpl implements MovieDao {

    private EntityManager entityManager;
    
    /**
     * Constructeur de l'EntityManager  .
     *
     */

    public MovieDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Recherche les films dans lesquels un acteur a joué.
     *
     * @param actorName Nom de l'acteur à rechercher.
     * @return Liste des films dans lesquels l'acteur a joué.
     */
    @Override
    public List<Movie> findMoviesByActorName(String actorName) {
        String dataBaseQuery = "SELECT m FROM Movie m JOIN m.actors a WHERE a.name = :actorName";
        TypedQuery<Movie> query = entityManager.createQuery(dataBaseQuery, Movie.class);
        query.setParameter("actorName", actorName.toLowerCase());
        return query.getResultList();
    }

    /**
     * Recherche les acteurs qui ont joué dans un film .
     *
     * @param movieName Nom du film à rechercher.
     * @return Liste des acteurs qui ont joué dans le film .
     */
    @Override
    public List<Actor> findActorsByMovieName(String movieName) {
        String dataBaseQuery = "SELECT a FROM Actor a JOIN a.movies m WHERE m.name = :movieName";
        TypedQuery<Actor> query = entityManager.createQuery(dataBaseQuery, Actor.class);
        query.setParameter("movieName", movieName.toLowerCase());
        return query.getResultList();
    }
    
    /**
     * Recherche les films sortis entre deux années.
     *
     * @param year1 Première année.
     * @param year2 Deuxième année.
     * @return Liste des films sortis entre year1 et year2.
     */
    @Override
    public List<Movie> findMoviesBetweenTwoYears(int year1 , int year2) {
        String dataBaseQuery = "SELECT m FROM Movie m WHERE m.year BETWEEN :year1 AND :year2";
        TypedQuery<Movie> query = entityManager.createQuery(dataBaseQuery, Movie.class);
        query.setParameter("year1", year1);
        query.setParameter("year2", year2);
        return query.getResultList();
    }
    
    /**
     * Recherche les films communs dans lesquels deux acteurs ont joué.
     *
     * @param actorName1 Nom du premier acteur.
     * @param actorName2 Nom du deuxième acteur.
     * @return Liste des films communs dans lesquels les deux acteurs ont joué.
     */
    @Override
    public List<Movie> findCommonMoviesByActors(String actorName1, String actorName2) {
        String dataBaseQuery = "SELECT m FROM Movie m JOIN m.actors a1 JOIN m.actors a2 WHERE a1.name = :actorName1 AND a2.name = :actorName2";
        TypedQuery<Movie> query = entityManager.createQuery(dataBaseQuery, Movie.class);
        query.setParameter("actorName1", actorName1.toLowerCase());
        query.setParameter("actorName2", actorName2.toLowerCase());
        return query.getResultList();
    }
    
    /**
     * Recherche les acteurs communs qui ont joué dans deux films spécifiques.
     *
     * @param movieName1 Nom du premier film.
     * @param movieName2 Nom du deuxième film.
     * @return Liste des acteurs communs qui ont joué dans les deux films .
     */
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
    /**
     * Recherche les films sortis entre deux années données dans lesquels un acteur a joué.
     *
     * @param year1   Première année.
     * @param year2   Deuxième année.
     * @param actorName Nom de l'acteur.
     * @return Liste des films sortis entre year1 et year2 dans lesquels l'acteur a joué.
     */
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
    /**
     * Recherche les films réalisés par un réalisateur .
     *
     * @param directorName Nom du réalisateur.
     * @return Liste des films réalisés par le réalisateur .
     */
    @Override
    public List<Movie> findMoviesByDirectorName(String directorName) {
        String databaseQuery = "SELECT m FROM Movie m JOIN m.directors d WHERE LOWER(d.name) = :directorName";
        TypedQuery<Movie> query = entityManager.createQuery(databaseQuery, Movie.class);
        query.setParameter("directorName", directorName.toLowerCase());
        return query.getResultList();
    }

  
}
