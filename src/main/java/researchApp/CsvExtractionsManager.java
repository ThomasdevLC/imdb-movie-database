package researchApp;

import readers.CountryCsvReader;
import readers.DirectorCsvReader;
import readers.ActorCsvReader;
import readers.MovieActorCsvReader;
import readers.MovieDirectorCsvReader;
import readers.RoleCsvReader;
import readers.MovieCsvReader;

/**
 * classe CsvExtractionManager gère l'extraction de données à partir de fichiers
 * CSV pour les classes/entités : pays, réalisateurs, acteurs, relations
 * acteur_film, relations réalisateur_film et films.
 */
public class CsvExtractionsManager {

	/**
	 * Méthode pour extraire toutes les données à partir des fichiers CSV. Appelle
	 * chaque lecteur CSV pour extraire les données et les persiste dans la base de
	 * données.
	 */
	public static void extractAll() {
		CountryCsvReader.extractCountries();
		ActorCsvReader.extractActors();
		DirectorCsvReader.extractDirectors();
		MovieCsvReader.extractMovies();
		RoleCsvReader.extractRoles();
		MovieActorCsvReader.extractMovieActors();
		MovieDirectorCsvReader.extractMovieDirectors();
	}

	public static void main(String[] args) {

		extractAll();

	}
}
