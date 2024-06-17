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

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "id_movie")
	private String idMovie;
	private String name;
	private int year;
	private double rating;
	private String url;
	@ManyToOne
	@JoinColumn(name = "id_language")
	private Language language;
	@Column(name = "synopsis", length = 2000)
	private String synopsis;
	@ManyToOne
	@JoinColumn(name = "id_country")
	private Country country;

	   @ManyToOne
	    @JoinColumn(name = "id_director")
	    private Director director;
	
	@ManyToMany
	@JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_genre", referencedColumnName = "id"))
	private List<Genre> genres;

	@ManyToMany
	@JoinTable(name = "movie_role", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"))
	private List<Role> roles;

	@ManyToMany
	@JoinTable(name = "movie_place", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_place", referencedColumnName = "id"))
	private List<Place> places;


	@ManyToMany
	@JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_actor", referencedColumnName = "id_actor"))
	private List<Actor> actors;

	public Movie(String idMovie, String name, int year, double rating, String url, Language language, String synopsis,
			Country country) {
		super();
		this.idMovie = idMovie;
		this.name = name;
		this.year = year;
		this.rating = rating;
		this.url = url;
		this.language = language;
		this.synopsis = synopsis;
		this.country = country;
		this.genres = new ArrayList<>();
		this.places = new ArrayList<>();
		
	}

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
	 * @return the places
	 */
	public List<Place> getPlaces() {
		return places;
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
	 * @param places the places to set
	 */
	public void setPlaces(List<Place> places) {
		this.places = places;
	}



	/**
	 * @param actors the actors to set
	 */
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", idMovie=" + idMovie + ", name=" + name + ", year=" + year + ", rating=" + rating
				+ ", url=" + url + ", language=" + language + ", synopsis=" + synopsis + ", country=" + country
				+ ", genres=" + genres + ", roles=" + roles + ", places=" + places + ", directors=" + director
				+ ", actors=" + actors + "]";
	}

	/**
	 * @return the director
	 */
	public Director getDirector() {
		return director;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirector(Director director) {
		this.director = director;
	}

}
