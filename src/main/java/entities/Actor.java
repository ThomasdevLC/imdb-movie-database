package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Classe représentant un acteur.
 */
@Entity
@Table(name = "actor")
public class Actor {

	/**
	 * Identifiant unique de l'acteur dans la base de données.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * Identifiant de l'acteur
	 */
	@Column(name = "id_actor")
	private String idActor;

	/**
	 * Nom de l'acteur.
	 */
	private String name;

	/**
	 * Date de naissance de l'acteur.
	 */
	@Column(name = "birth_date")
	private LocalDate birthDate;

	/**
	 * Lieu de naissance de l'acteur.
	 */

	@ManyToOne
	@JoinColumn(name = "id_place")
	private BirthPlace birthPlace;

	/**
	 * Taille de l'acteur en mètres.
	 */
	private double height;

	/**
	 * URL de de la page web de l'acteur.
	 */
	private String url;

	/**
	 * Liste des films dans lesquels l'acteur a joué.
	 */
	@ManyToMany
	@JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "id_actor", referencedColumnName = "id_actor"), inverseJoinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"))
	private List<Movie> movies;

	/**
	 * Constructeur de la classe Actor.
	 *
	 * @param idActor    Identifiant de l'acteur
	 * @param name       Nom de l'acteur
	 * @param birthDate  Date de naissance de l'acteur
	 * @param birthPlace Lieu de naissance de l'acteur
	 * @param height     Taille de l'acteur
	 * @param url        URL de l'acteur
	 */
	public Actor(String idActor, String name, LocalDate birthDate, BirthPlace birthPlace, Double height, String url) {
		super();
		this.idActor = idActor;
		this.name = name;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		this.height = height;
		this.url = url;
		this.movies = new ArrayList<>();
	}

	public Actor() {
		super();

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the idActor
	 */
	public String getIdActor() {
		return idActor;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @return the birthPlace
	 */
	public BirthPlace getBirthPlace() {
		return birthPlace;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
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
	 * @param idActor the idActor to set
	 */
	public void setIdActor(String idActor) {
		this.idActor = idActor;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @param birthPlace the birthPlace to set
	 */
	public void setBirthPlace(BirthPlace birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param movies the movies to set
	 */
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", idActor=" + idActor + ", name=" + name + ", birthDate=" + birthDate
				+ ", birthPlace=" + birthPlace + ", height=" + height + ", url=" + url + ", movies=" + movies + "]";
	}

}
