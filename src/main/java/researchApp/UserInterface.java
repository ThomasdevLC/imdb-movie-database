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
        // Initialisation de l'EntityManagerFactory et de l'EntityManager
        emf = Persistence.createEntityManagerFactory("movie_database");
        em = emf.createEntityManager();
        // Initialisation de MovieDao avec l'EntityManager
        movieDAO = new MovieDaoImpl(em);
        // Initialisation du Scanner pour lire les entrées utilisateur
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        // Affiche le menu des options à l'utilisateur
        System.out.println("");
        System.out.println("Choisissez une option:");
        System.out.println("1. Trouver des films par le nom de l'acteur");
        System.out.println("2. Trouver des acteurs par le nom du film");
        System.out.println("3. Trouver des films sortis entre deux années");
        System.out.println("4. Trouver des films communs à deux acteurs");
        System.out.println("5. Trouver des acteurs communs à deux films");
        System.out.println("6. Trouver des films d'un acteur sortis entre deux années");
        System.out.println("0. Quitter");
    }

    public void userSelection() {
        int option = -1;
        do {
            displayMenu();
            System.out.print("Entrez votre choix: ");
            
            // Lire l'entrée utilisateur avec gestion des exceptions
            try {
                option = Integer.parseInt(scanner.nextLine().trim()); 
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un numéro correspondant à une option du menu.");
                continue;  
            }

            switch (option) {
                case 1:
                    System.out.print("Entrez le nom de l'acteur: ");
                    String actorName = scanner.nextLine();
                    List<Movie> moviesForActor = movieDAO.findMoviesByActorName(actorName);
                    System.out.println("Films pour l'acteur " + actorName + ":");
                    for (Movie movie : moviesForActor) {
                        System.out.println(movie.getName());
                    }
                    break;

                case 2:
                    System.out.print("Entrez le nom du film: ");
                    String movieName = scanner.nextLine();
                    List<Actor> actorsForMovie = movieDAO.findActorsByMovieName(movieName);
                    System.out.println("Acteurs pour le film '" + movieName + "':");
                    for (Actor actor : actorsForMovie) {
                        System.out.println(actor.getName());
                    }
                    break;

                case 3:
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
                        System.out.println(movie.getName() + " " + movie.getYear());
                    }
                    break;

                case 0:
                    System.out.println("Quitter l'application.");
                    break;

                default:
                    System.out.println("Option non valide.");
            }
        } while (option != 0);

        // Fermeture des ressources
        em.close();
        emf.close();
        scanner.close();
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.userSelection();
    }
}

