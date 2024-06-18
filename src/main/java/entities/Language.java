package entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/**
 * Classe  représentant une langue.
 */
@Entity
@Table(name = "language")
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	 /** 
     * Liste des films associés à une langue.
     */
	@OneToMany(mappedBy = "language")
	private List<Movie> movies;

	/**
     * Constructeur pour créer un nouvel objet Langue avec le nom donné.
     * @param name Le nom de la langue.
     */
	public Language(String name) {
		super();
		this.name = name;
	}
	 /**
     * Constructeur par défaut.
     */
	public Language() {
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
		return "Language [id=" + id + ", name=" + name + ", movies=" + movies + "]";
	}

}
