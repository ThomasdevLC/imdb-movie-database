package utils;

import jakarta.persistence.EntityManager;
import entities.Country;
import entities.Place;
/**
 * Classe utilitaire pour la gestion de recherche ou création d'entités Place et Country
 * dans la table de données des réalisateurs.
 * Utilisé dans la classe {@link DirectorCsvReader} pour gérer les données relatives au lieu de naissance.

 */
public class DirectorCheckDatabaseUtil {
	
	  /**
     * Recherche ou crée une entité Place dans la base de données en fonction de la ville (city), de l'état (state)
     * et du nom du pays (countryName).
     *
     * @param em          EntityManager pour la persistance de données.
     * @param city        Le nom de la ville .
     * @param state       Le nom de l'état .
     * @param countryName Le nom du pays .
     * @return L'entité Place qui existe déjà ou nouvellement créé.
     */

	public static Place findOrCreatePlace(EntityManager em, String state, String city, String countryName) {
	    Place place = em.createQuery("SELECT p FROM Place p WHERE p.city = :city AND p.state = :state", Place.class)
	            .setParameter("city", city)
	            .setParameter("state", state)
	            .getResultList()
	            .stream()
	            .findFirst()
	            .orElse(null);

	    if (place == null) {
	        if (countryName != null) {
	            Country country = findOrCreateCountry(em, countryName);
	            place = new Place(state, city, country);
	        } else {
	            place = new Place(state, city, null); 
	        }
	        em.persist(place);
	    }

	    return place;
	}

	 /**
     * Recherche ou crée une entité Country dans la base de données en fonction du nom du pays (countryName).
     *
     * @param EntityManager pour la persistance de données.
     * @param countryName Le nom du pays
     * @return L'entité Country qui existe déjà ou nouvellement créée .
     */
	
    public static Country findOrCreateCountry(EntityManager em, String countryName) {
        Country country = em.createQuery("SELECT c FROM Country c WHERE c.name = :name", Country.class)
                .setParameter("name", countryName)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        if (country == null) {
            country = new Country(countryName);
            em.persist(country);
        }

        return country;
    }
}
