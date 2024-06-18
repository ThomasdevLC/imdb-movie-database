package readers;

/**
 *  classe CsvExtractionManager gère l'extraction de données à partir de fichiers CSV pour les classes/entités :
 * pays, réalisateurs, acteurs, relations acteur_film, relations réalisateur_film et films.
 */
public class CsvExtractionManager {

    private final CountryCsvReader countryCsvReader;
    private final DirectorCsvReader directorCsvReader;
    private final ActorCsvReader actorCsvReader;
    private final MovieActorCsvReader movieActorCsvReader;
    private final MovieDirectorCsvReader movieDirectorCsvReader;
    private final MovieCsvReader movieCsvReader;

    /**
     * Constructeur pour initialiser le gestionnaire d'extraction avec les différents lecteurs CSV.
     *
     * @param countryCsvReader      Lecteur de fichiers CSV pour les pays.
     * @param directorCsvReader     Lecteur de fichiers CSV pour les réalisateurs.
     * @param actorCsvReader        Lecteur de fichiers CSV pour les acteurs.
     * @param movieActorCsvReader   Lecteur de fichiers CSV pour les relations acteur-film.
     * @param movieDirectorCsvReader Lecteur de fichiers CSV pour les relations réalisateur-film.
     * @param movieCsvReader        Lecteur de fichiers CSV pour les films.
     */
    public CsvExtractionManager(CountryCsvReader countryCsvReader, DirectorCsvReader directorCsvReader,
                                ActorCsvReader actorCsvReader, MovieActorCsvReader movieActorCsvReader,
                                MovieDirectorCsvReader movieDirectorCsvReader, MovieCsvReader movieCsvReader) {
        this.countryCsvReader = countryCsvReader;
        this.directorCsvReader = directorCsvReader;
        this.actorCsvReader = actorCsvReader;
        this.movieActorCsvReader = movieActorCsvReader;
        this.movieDirectorCsvReader = movieDirectorCsvReader;
        this.movieCsvReader = movieCsvReader;
    }

    /**
     * Méthode  pour extraire toutes les données à partir des fichiers CSV.
     * Appelle chaque lecteur CSV pour extraire les données et les persiste dans la base de données.
     */
    public void extractAll() {
        countryCsvReader.extractCountries();
        directorCsvReader.extractDirectors();
        actorCsvReader.extractActors();
        movieActorCsvReader.extractMovieActors();
        movieDirectorCsvReader.extractMovieDirectors();
        movieCsvReader.extractMovies();
    }

    /**
     * Méthode principale pour démarrer l'extraction globale en créant une instance de CsvExtractionManager
     * Appelle extractAll() pour démarrer l'extraction.
     *
     */
    public static void main(String[] args) {
        CountryCsvReader countryReader = new CountryCsvReader();
        DirectorCsvReader directorReader = new DirectorCsvReader();
        ActorCsvReader actorCsvReader = new ActorCsvReader();
        MovieActorCsvReader movieActorCsvReader = new MovieActorCsvReader();
        MovieDirectorCsvReader movieDirectorCsvReader = new MovieDirectorCsvReader();
        MovieCsvReader movieCsvReader = new MovieCsvReader();
        
        long startTime = System.currentTimeMillis();


        CsvExtractionManager manager = new CsvExtractionManager(countryReader, directorReader, actorCsvReader,
                movieActorCsvReader, movieDirectorCsvReader, movieCsvReader);
        manager.extractAll();
        
        long endTime = System.currentTimeMillis();
        long executionTimeMillis = endTime - startTime;

        // Convertir le temps d'exécution en minutes et secondes
        long minutes = (executionTimeMillis / 1000) / 60;
        long seconds = (executionTimeMillis / 1000) % 60;

        System.out.println("Temps d'exécution total : " + minutes + " minutes et " + seconds + " secondes");
    }
}
