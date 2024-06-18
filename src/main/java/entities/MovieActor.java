package entities;

import jakarta.persistence.*;
/**
 * Classe entité représentant la relation entre un film et un acteur.
 */
@Entity
@Table(name = "movie_actor")
public class MovieActor {
    /** Identifiant unique de la relation entre le film et l'acteur. */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_movie")
    private String idMovie;

    @Column(name = "id_actor")
    private String idActor;

    public MovieActor() {
    }
    /**
     * Constructeur pour créer un nouvel objet MovieActor.
     *
     * @param idMovie Identifiant du film.
     * @param idActor Identifiant de l'acteur.
     */
    public MovieActor(String idMovie, String idActor) {
        this.idMovie = idMovie;
        this.idActor = idActor;
    }

    // Getters and Setters

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getIdActor() {
        return idActor;
    }

    public void setIdActor(String idActor) {
        this.idActor = idActor;
    }

    @Override
    public String toString() {
        return "MovieActor [id=" + id + ", idMovie=" + idMovie + ", idActor=" + idActor + "]";
    }
}

