package entities;

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
 * Classe entité représentant un film.
 */
@Entity
@Table(name = "movie")
public class Movie {
	/** Identifiant unique du film. */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "id_movie")
	private String idMovie;
	private String name;
	private int year;
	private double rating;
	private String url;
	private String shootingLocation;
	@ManyToOne
	@JoinColumn(name = "id_language")
	private Language language;
	@Column(name = "synopsis", length = 2000)
	private String synopsis;
	@ManyToOne
	@JoinColumn(name = "id_country")
	private Country country;

	/** Liste des genres du film. */
	@ManyToMany
	@JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_genre", referencedColumnName = "id"))
	private List<Genre> genres;

	/** Liste des rôles dans le film. */
	@ManyToMany
	@JoinTable(name = "movie_role", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"))
	private List<Role> roles;

	/** Liste des acteurs dans le film. */
	@ManyToMany
	@JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_actor", referencedColumnName = "id_actor"))
	private List<Actor> actors;

	/** Liste des réalisateurs du film. */
	@ManyToMany
	@JoinTable(name = "movie_director", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_director", referencedColumnName = "id_director"))
	private List<Director> directors;

	/**
	 * Constructeur pour créer un nouvel objet Movie avec ses attributs .
	 *
	 * @param idMovie  Identifiant du film.
	 * @param name     Nom du film.
	 * @param year     Année de sortie du film.
	 * @param rating   Note du film.
	 * @param url      URL du film.
	 * @param language Langue du film.
	 * @param synopsis Synopsis du film.
	 * @param country  Pays d'origine du film.
	 */
	public Movie(String idMovie, String name, int year, double rating, String url,String shootingLocation, Language language, String synopsis,
			Country country) {
		super();
		this.idMovie = idMovie;
		this.name = name;
		this.year = year;
		this.rating = rating;
		this.url = url;
		this.shootingLocation=shootingLocation;
		this.language = language;
		this.synopsis = synopsis;
		this.country = country;
		this.genres = new ArrayList<>();

	}

	/**
	 * Constructeur par défaut.
	 */
	public Movie() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the idMovie
	 */
	public String getIdMovie() {
		return idMovie;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * @return the synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @return the actors
	 */
	public List<Actor> getActors() {
		return actors;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param idMovie the idMovie to set
	 */
	public void setIdMovie(String idMovie) {
		this.idMovie = idMovie;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * @param synopsis the synopsis to set
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	/**
	 * @param actors the actors to set
	 */
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	/**
	 * @return the directors
	 */
	public List<Director> getdirectors() {
		return directors;
	}

	/**
	 * @return the shootingLocation
	 */
	public String getShootingLocation() {
		return shootingLocation;
	}

	/**
	 * @return the directors
	 */
	public List<Director> getDirectors() {
		return directors;
	}

	/**
	 * @param shootingLocation the shootingLocation to set
	 */
	public void setShootingLocation(String shootingLocation) {
		this.shootingLocation = shootingLocation;
	}

	/**
	 * @param directors the directors to set
	 */
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", idMovie=" + idMovie + ", name=" + name + ", year=" + year + ", rating=" + rating
				+ ", url=" + url + ", shootingLocation=" + shootingLocation + ", language=" + language + ", synopsis="
				+ synopsis + ", country=" + country + ", genres=" + genres + ", roles=" + roles + ", actors=" + actors
				+ ", directors=" + directors + "]";
	}
	
	
}
