package entities;

import java.time.LocalDate;
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
 * Classe entité représentant un réalisateur.
 */
@Entity
@Table(name = "director")
public class Director {
    /** Identifiant unique du réalisateur. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    /** Identifiant du réalisateur. */

	@Column(name = "id_director")
	private String idDirector;
    
	/** Nom du réalisateur. */
	private String name;
	
    /** Date de naissance du réalisateur. */
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
    /** Lieu de naissance du réalisateur. */
	@ManyToOne
	@JoinColumn(name = "id_place")
	private Place birthPlace;
	
    /** URL de la page web du du réalisateur. */
	private String url;

    /** Liste des films réalisés par ce réalisateur. */
	@ManyToMany
	@JoinTable(name = "movie_director", joinColumns = @JoinColumn(name = "id_director", referencedColumnName = "id_director"), inverseJoinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"))
	private List<Movie> movies;

	  /**
     * Constructeur pour créer un nouvel objet Director avec ses attributs.
     *
     * @param idDirector Identifiant du réalisateur.
     * @param name Nom du réalisateur.
     * @param birthDate Date de naissance du réalisateur.
     * @param birthPlace Lieu de naissance du réalisateur.
     * @param url URL de la page web ou du profil du réalisateur.
     */
	public Director(String idDirector, String name, LocalDate birthDate, Place birthPlace, String url) {
		super();
		this.idDirector = idDirector;
		this.name = name;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		this.url = url;
	}
	
	 /**
     * Constructeur par défaut.
     */

	public Director() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the idDirector
	 */
	public String getIdDirector() {
		return idDirector;
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
	public Place getBirthPlace() {
		return birthPlace;
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
	 * @param idDirector the idDirector to set
	 */
	public void setIdDirector(String idDirector) {
		this.idDirector = idDirector;
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
	public void setBirthPlace(Place birthPlace) {
		this.birthPlace = birthPlace;
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
		return "Director [id=" + id + ", idDirector=" + idDirector + ", name=" + name + ", birthDate=" + birthDate
				+ ", birthPlace=" + birthPlace + ", url=" + url + ", movies=" + movies + "]";
	}

}
