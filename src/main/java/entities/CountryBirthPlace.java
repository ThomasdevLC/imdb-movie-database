package entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

	/**
	 * Classe  représentant un pays.
	 */
	@Entity
	@Table(name = "country_birth_place")
	public class CountryBirthPlace {
	    
	    /** Identifiant unique du pays. */
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    
	    /** Le nom du pays. */
	    private String name;
	    
	    /** 
	     * Liste des lieux associés à ce pays.
	     *  représente une relation de un à plusieurs avec les lieux où ce pays est référencé.
	     */
	    @OneToMany(mappedBy = "countryBirthPlace")
	    private List<BirthPlace> birthPlaces;
	    
	    /**
	     * Constructeur pour créer un nouvel objet countryBirthPlace avec le nom donné.
	     * @param name Le nom du pays.
	     */
	    public CountryBirthPlace(String name) {
	        super();
	        this.name = name;
	    }

	    /**
	     * Constructeur par défaut.
	     */
	    public CountryBirthPlace() {
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

		@Override
		public String toString() {
			return "CountryBirthPlace [id=" + id + ", name=" + name + "]";
		}
	
}
