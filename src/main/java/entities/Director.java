package entities;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "director")
public class Director {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "id_director")
	private String idDirector;
	private String name;
	@Column(name = "birth_date")
	private LocalDate birthDate;
//	@Column(name = "birth_place")
	@ManyToOne
	@JoinColumn(name = "id_place")
	private Place birthPlace;
	private String url;


	@OneToMany(mappedBy = "director")
	private List<Movie> movies;

	public Director(String idDirector, String name, LocalDate birthDate, Place birthPlace, String url) {
		super();
		this.idDirector = idDirector;
		this.name = name;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		this.url = url;
	}

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
