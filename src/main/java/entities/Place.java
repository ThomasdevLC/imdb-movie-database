package entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "place")
public class Place {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String city;
	private String state;

	@ManyToOne
	@JoinColumn(name = "id_country")
	private Country country;

	@ManyToMany
	@JoinTable(name = "movie_place", joinColumns = @JoinColumn(name = "id_place", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"))
	private List<Movie> movies;

	@OneToMany(mappedBy = "birthPlace")
	private List<Director> directors;

	@OneToMany(mappedBy = "birthPlace")
	private List<Actor> actors;

	public Place(String city, String state, Country country) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public Place() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the state
	 */
	public String getStreet() {
		return state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @return the movies
	 */
	public List<Movie> getMovies() {
		return movies;
	}

	/**
	 * @return the directors
	 */
	public List<Director> getDirectors() {
		return directors;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param state the state to set
	 */
	public void setStreet(String state) {
		this.state = state;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @param movies the movies to set
	 */
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	/**
	 * @param directors the directors to set
	 */
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", state=" + state + ", city=" + city + ", country=" + country + ", movies="
				+ movies + ", directors=" + directors + "]";
	}

	/**
	 * @return the actors
	 */
	public List<Actor> getActors() {
		return actors;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

}
