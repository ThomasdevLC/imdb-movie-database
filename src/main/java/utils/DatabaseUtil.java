package utils;

import jakarta.persistence.EntityManager;
import entities.Country;
import entities.Place;


public class DatabaseUtil {
	

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
