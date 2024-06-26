package utils;

import jakarta.persistence.EntityManager;
import entities.CountryBirthPlace;
import entities.BirthPlace;
/**
 * Classe utilitaire pour la gestion de recherche ou création d'entités Place et Country
 * dans la table de données des réalisateurs.
 * Utilisé dans la classe {@link DirectorCsvReader} {@link ActorCsvReader}  pour gérer les données relatives au lieu de naissance.

 */
public class PlaceCheckDatabaseUtil {
	
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

	public static BirthPlace findOrCreatePlace(EntityManager em, String state, String city, String countryName) {
		BirthPlace birthPlace = em.createQuery("SELECT p FROM BirthPlace p WHERE p.city = :city AND p.state = :state", BirthPlace.class)
	            .setParameter("city", city)
	            .setParameter("state", state)
	            .getResultList()
	            .stream()
	            .findFirst()
	            .orElse(null);

	    if (birthPlace == null) {
	        if (countryName != null) {
	        	CountryBirthPlace countryBirthPlace = findOrCreateCountry(em, countryName);
	            birthPlace = new BirthPlace(state, city, countryBirthPlace);
	        } else {
	            birthPlace = new BirthPlace(state, city, null); 
	        }
	        em.persist(birthPlace);
	    }

	    return birthPlace;
	}

	 /**
     * Recherche ou crée une entité Country dans la base de données en fonction du nom du pays (countryName).
     *
     * @param EntityManager pour la persistance de données.
     * @param countryName Le nom du pays
     * @return L'entité Country qui existe déjà ou nouvellement créée .
     */
	
    public static CountryBirthPlace findOrCreateCountry(EntityManager em, String countryName) {
    	CountryBirthPlace countryBirthPlace = em.createQuery("SELECT c FROM CountryBirthPlace c WHERE c.name = :name", CountryBirthPlace.class)
                .setParameter("name", countryName)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        if (countryBirthPlace == null) {
        	countryBirthPlace = new CountryBirthPlace(countryName);
            em.persist(countryBirthPlace);
        }

        return countryBirthPlace;
    }
}
