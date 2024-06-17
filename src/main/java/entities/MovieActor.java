package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_actor")
public class MovieActor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_movie")
    private String idMovie;

    @Column(name = "id_actor")
    private String idActor;

    public MovieActor() {
    }

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

