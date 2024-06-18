package researchApp;

import java.util.List;
import java.util.Scanner;

import entities.Actor;
import entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import queries.MovieDao;
import queries.MovieDaoImpl;

public class UserInterface {

    private EntityManagerFactory emf;
    private EntityManager em;
    private MovieDao movieDAO;
    private Scanner scanner;

    public UserInterface() {
        emf = Persistence.createEntityManagerFactory("movie_database");
        em = emf.createEntityManager();
        
        movieDAO = new MovieDaoImpl(em);
        
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("");
        System.out.println("Choisissez une option:");
        System.out.println("1. Filmographie d’un acteur ");
        System.out.println("2. Casting d’un film donné");
        System.out.println("3. Films sortis entre 2 années ");
        System.out.println("4. Films communs à 2 acteurs/actrices donné");
        System.out.println("5. Acteurs communs à 2 films donnés");
        System.out.println("6. Films d'un acteur sortis entre deux années");
        System.out.println("7. Films d'un réalisateur ");

        System.out.println("8. Quitter");
    }

    public void userSelection() {
        int option = -1;
        do {
            displayMenu();
            System.out.print("Saisir votre choix: ");
            
            try {
                option = Integer.parseInt(scanner.nextLine().trim()); 
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un numéro correspondant à une option du menu.");
                continue;  
            }

            switch (option) {
                case 1:
                	//exemple : martin sheen
                    System.out.print("Entrez le nom de l'acteur: ");
                    String actorName = scanner.nextLine();
                    List<Movie> moviesForActor = movieDAO.findMoviesByActorName(actorName);
                    System.out.println("Films de l'acteur " + actorName + ":");
                    for (Movie movie : moviesForActor) {
                        System.out.println(movie.getName());
                    }
                    break;

                case 2:
                	//exemple : galactica
                    System.out.print("Entrez le nom du film: ");
                    String movieName = scanner.nextLine();
                    List<Actor> actorsForMovie = movieDAO.findActorsByMovieName(movieName);
                    System.out.println("Acteurs pour le film '" + movieName + "':");
                    for (Actor actor : actorsForMovie) {
                        System.out.println(actor.getName());
                    }
                    break;

                case 3:
                	//exemple : Craig Wasson  +  Alice Krige
                    System.out.print("Entrez l'année de début: ");
                    int year1 = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Entrez l'année de fin: ");
                    int year2 = Integer.parseInt(scanner.nextLine().trim());
                    List<Movie> releasedMovies = movieDAO.findMoviesBetweenTwoYears(year1, year2);
                    System.out.println("Films réalisés entre " + year1 + " et " + year2 + ":");
                    for (Movie movie : releasedMovies) {
                        System.out.println(movie.getName());
                    }
                    break;

                case 4:
                	//exemple Robert De Niro  + Edward Norton
                    System.out.print("Entrez le nom du premier acteur: ");
                    String actorName1 = scanner.nextLine();
                    System.out.print("Entrez le nom du deuxième acteur: ");
                    String actorName2 = scanner.nextLine();
                    List<Movie> commonMovies = movieDAO.findCommonMoviesByActors(actorName1, actorName2);
                    System.out.println("Films communs à " + actorName1 + " et " + actorName2 + ":");
                    for (Movie movie : commonMovies) {
                        System.out.println(movie.getName());
                    }
                    break;

                case 5:
                    System.out.print("Entrez le nom du premier film: ");
                    String movieName1 = scanner.nextLine();
                    System.out.print("Entrez le nom du deuxième film: ");
                    String movieName2 = scanner.nextLine();
                    List<Actor> commonActors = movieDAO.findCommonActorsByMovies(movieName1, movieName2);
                    System.out.println("Acteurs communs à " + movieName1 + " et " + movieName2 + ":");
                    for (Actor actor : commonActors) {
                        System.out.println(actor.getName());
                    }
                    break;

                case 6:
                    System.out.print("Entrez l'année de début: ");
                    int yearOne = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Entrez l'année de fin: ");
                    int yearTwo = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Entrez le nom de l'acteur: ");
                    String actor = scanner.nextLine();
                    List<Movie> releasedMoviesByPeriodByActor = movieDAO.findMoviesBetweenTwoYearsbyActor(yearOne, yearTwo, actor);
                    System.out.println("Films réalisés entre " + yearOne + " et " + yearTwo + " avec " + actor + ":");
                    for (Movie movie : releasedMoviesByPeriodByActor) {
                        System.out.println(movie.getName() + " - " + movie.getYear());
                    }
                    break;

                case 7:
                	//exemple : Lewin Webb
                	System.out.print("Entrez le nom du directeur: ");
                    String directorName = scanner.nextLine();
                    List<Movie> moviesForDirector = movieDAO.findMoviesByDirectorName(directorName);
                    System.out.println("Films du réalisateur " + directorName + ":");
                    for (Movie movie : moviesForDirector) {
                        System.out.println(movie.getName());
                    }
                    break;
                    
                case 8:
                    System.out.println("Application quittée");
                    break;

                default:
                    System.out.println("Option non valide.");
            }
        } while (option != 8);

        em.close();
        emf.close();
        scanner.close();
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.userSelection();
    }
}

