package entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
/**
 * Classe  représentant un genre.
 */
@Entity
@Table(name = "genre")
public class Genre {
    /** Identifiant unique du genre. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** Le nom du genre. */
	private String name;

	 /** 
     * Liste des films associés à ce genre.
     */
	@ManyToMany
	@JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "id_genre", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"))
	private List<Movie> movies;

	  /**
     * Constructeur pour créer un nouvel objet Genre avec le nom donné.
     * @param name Le nom du genre.
     */
	public Genre(String name) {
		super();
		this.name = name;
	}
	 /**
     * Constructeur par défaut.
     */
	public Genre() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the movies
	 */
	public List<Movie> getMovies() {
		return movies;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param movies the movies to set
	 */
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + ", movies=" + movies + "]";
	}

	
}
