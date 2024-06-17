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

@Entity
@Table(name = "actor")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "id_actor")
	private String idActor;
	private String name;
	@Column(name = "birth_date")
	private LocalDate birthDate;
//	@Column(name = "birth_place")
	@ManyToOne
	@JoinColumn(name = "id_place")
	private Place birthPlace;
	private double height;
	private String url;

	@ManyToMany
	@JoinTable(name = "actor_role", joinColumns = @JoinColumn(name = "id_actor", referencedColumnName = "id_actor"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"))
	private List<Role> roles;

	@ManyToMany
	@JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "id_actor", referencedColumnName = "id_actor"), inverseJoinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"))
	private List<Movie> movies;

	public Actor(String idActor, String name, LocalDate birthDate, Place birthPlace, Double height, String url) {
		super();
		this.idActor = idActor;
		this.name = name;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		this.height = height;
		this.url = url;
		this.roles = new ArrayList<>();
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
	public Place getBirthPlace() {
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
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
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
	public void setBirthPlace(Place birthPlace) {
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
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
				+ ", birthPlace=" + birthPlace + ", height=" + height + ", url=" + url + ", roles=" + roles
				+ ", movies=" + movies + "]";
	}

}
