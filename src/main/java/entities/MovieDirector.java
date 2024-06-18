package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * Classe entité représentant la relation entre un film et un réalisateur.
 */
@Entity
@Table(name = "movie_director")

public class MovieDirector {
    /** Identifiant unique de la relation entre le film et réalisateur. */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_movie")
    private String idMovie;

    @Column(name = "id_director")
    private String idDirector;

    public MovieDirector() {
    }

    /**
     * Constructeur pour créer un nouvel objet MovieDirector.
     *
     * @param idMovie Identifiant du film.
     * @param idActor Identifiant du réalisateur.
     */
    public MovieDirector(String idMovie, String idDirector) {
        this.idMovie = idMovie;
        this.idDirector = idDirector;
    }

    // Getters and Setters

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getIDirector() {
        return idDirector;
    }

    public void setIdDirector(String idDirector) {
        this.idDirector = idDirector;
    }

    @Override
    public String toString() {
        return "MovieActor [id=" + id + ", idMovie=" + idMovie + ", idDirector=" + idDirector + "]";
    }
}
