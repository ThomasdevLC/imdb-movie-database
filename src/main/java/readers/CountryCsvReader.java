package readers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.CsvFileUtil;
import entities.Country;

/**
 * classe lit un fichier CSV contenant des noms de pays et les persiste en base
 * de données.
 */
public class CountryCsvReader {
	
	/**
	 * Méthode principale qui lit le fichier CSV des pays et les persiste dans la
	 * base de données.
	 */
	
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_database");
		EntityManager em = emf.createEntityManager();

		String fileName = "pays.csv";

		em.getTransaction().begin();

		try {
			Path path = CsvFileUtil.getPath(fileName);

			List<String> allLines = Files.readAllLines(path);
			List<String> dataLines = allLines.subList(1, allLines.size());
			Set<String> uniqueCountries = new HashSet<>();

			for (String line : dataLines) {
				String[] col = line.split(";");

				if (col.length > 0) {
					String countryName = col[0];
					uniqueCountries.add(countryName);
				}
			}

			for (String countryName : uniqueCountries) {
				Country country = new Country(countryName);
				em.persist(country);
				System.out.println("database country: " + country.getName());
			}

			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();

			System.out.println("Error : " + e.getMessage());
		} finally {

			em.close();
			emf.close();

		}
	}
}
