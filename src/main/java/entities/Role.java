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
import jakarta.persistence.Table;
/**
 * Classe  représentant un rôle.
 */
@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	private int idRole;
	@Column(name = "id_movie")
	private String idMovie;
	@Column(name = "id_actor")
	private String idActor;
	private String name;
	 
	/** 
     * Liste des films associés à ce rôle.
     */
	@ManyToMany
	@JoinTable(name = "movie_role", joinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"), inverseJoinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"))
	private List<Movie> movies;

	 /** 
     * Liste des acteurs associés à ce rôle.
     */
	@ManyToMany
	@JoinTable(name = "actor_role", joinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"), inverseJoinColumns = @JoinColumn(name = "id_actor", referencedColumnName = "id_actor"))
	private List<Actor> actors;
	
	
	 /**
     * Constructeur pour créer un nouvel objet Role.
     *
     * @param idMovie Identifiant du film.
     * @param idActor Identifiant de l'acteur.
     * @param name Nom du rôle.
     */

	public Role(String idMovie, String idActor, String name) {
		super();
		this.idMovie = idMovie;
		this.idActor = idActor;
		this.name = name;
		this.actors = new ArrayList<>();
		this.movies = new ArrayList<>();
	}

	public Role() {
		super();
	}

	/**
	 * @return the idRole
	 */
	public int getIdRole() {
		return idRole;
	}

	/**
	 * @return the idMovie
	 */
	public String getIdMovie() {
		return idMovie;
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
	 * @return the movies
	 */
	public List<Movie> getMovies() {
		return movies;
	}

	/**
	 * @return the actors
	 */
	public List<Actor> getActors() {
		return actors;
	}

	/**
	 * @param idRole the idRole to set
	 */
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	/**
	 * @param idMovie the idMovie to set
	 */
	public void setIdMovie(String idMovie) {
		this.idMovie = idMovie;
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
	 * @param movies the movies to set
	 */
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", idMovie=" + idMovie + ", idActor=" + idActor + ", name=" + name
				+ ", movies=" + movies + ", actors=" + actors + "]";
	}
	

}
