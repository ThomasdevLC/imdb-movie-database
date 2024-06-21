package entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Classe  représentant un lieu.
 */
@Entity
@Table(name = "birth_place")
public class BirthPlace {
    /** Identifiant unique du lieu. */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String city;
	private String state;
	
	 /** 
     *  Pays associé à ce lieu.
     */
	@ManyToOne
	@JoinColumn(name = "id_country_birthplace")
	private CountryBirthPlace countryBirthPlace;

	
	 /** 
     * Liste des réalisateurs associés à ce lieu.
     */
	@OneToMany(mappedBy = "birthPlace")
	private List<Director> directors;
	
	 /** 
     * Liste des acteurs associés à ce lieu.
     */
	@OneToMany(mappedBy = "birthPlace")
	private List<Actor> actors;

	 /**
     * Constructeur pour créer un nouvel objet Place.
     * @param city Le nom de la ville.
     * @param state Le nom de la région ou état.
     * @param country Le nom du pays.

     */
	public BirthPlace(String city, String state, CountryBirthPlace countryBirthPlace) {
		super();
		this.city = city;
		this.state = state;
		this.countryBirthPlace = countryBirthPlace;
	}
	
	/**
     * Constructeur par défaut.
     */
	public BirthPlace() {
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
	public CountryBirthPlace getCountry() {
		return countryBirthPlace;
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
	public void setCountry(CountryBirthPlace countryBirthPlace) {
		this.countryBirthPlace = countryBirthPlace;
	}


	/**
	 * @param directors the directors to set
	 */
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
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
	
	@Override
	public String toString() {
		return "Place [id=" + id + ", state=" + state + ", city=" + city + ", country=" + countryBirthPlace + 
				 ", directors=" + directors + "]";
	}
}
