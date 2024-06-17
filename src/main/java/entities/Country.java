package entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@OneToMany(mappedBy = "country")
	private List<Movie> movies;
	
	@OneToMany(mappedBy = "country")
	private List<Place> places;

	public Country(String name) {
		super();
		this.name = name;
	}

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
	 * @return the places
	 */
	public List<Place> getPlaces() {
		return places;
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

	/**
	 * @param places the places to set
	 */
	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", movies=" + movies + ", places=" + places + "]";
	}
	

}