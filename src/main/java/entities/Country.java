package entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Classe représentant un pays.
 */
@Entity
@Table(name = "country")
public class Country {

	/** Identifiant unique du pays. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** Le nom du pays. */
	private String name;

	/**
	 * Liste des films associés à ce pays.
	 */
	@OneToMany(mappedBy = "country")
	private List<Movie> movies;

	/**
	 * Constructeur pour créer un nouvel objet Country avec le nom donné.
	 * 
	 * @param name Le nom du pays.
	 */
	public Country(String name) {
		super();
		this.name = name;
	}

	/**
	 * Constructeur par défaut.
	 */
	public Country() {
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
		return "Country [id=" + id + ", name=" + name + ", movies=" + movies + "]";
	}

}