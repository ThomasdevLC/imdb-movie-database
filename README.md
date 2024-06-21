

# Internet Movie DataBase Project
Ce projet implémente une base de données de films à partir de fichiers CSV et permet aux utilisateurs de rechercher des informations spécifiques sur les films, les acteurs et les réalisateurs.

## Contenu du projet
Les données sont réparties dans les fichiers suivants :

films.csv : liste des films
acteurs.csv : liste des acteurs
realisateurs.csv : liste des réalisateurs
roles.csv : listes des rôles par films
film_realisateurs.csv : réalisateurs par films
pays.csv : liste des pays
Objectifs du projet
Les objectifs principaux de ce projet sont :

Réaliser un document de conception incluant :

Diagramme de classes (UML) et/ou diagramme entités relations
Mettre en place une base de données à partir des fichiers fournis.

Développer une application pour :

Parser les fichiers CSV (ou JSON avec Jackson) et insérer les données en base de données (utilisant JPA).
Fournir un menu interactif pour extraire des informations spécifiques.

## Exigences du projet
Exigence n°1 : Qualité de code
Documenter le code avec Javadoc.
Structurer le code de manière modulaire et claire.
Exigence n°2 : Pas de duplication des données en base
Utiliser des classes pour les données telles que Lieu de naissance, pays, langue, genre.
Assurer l'unicité pour les lieux de naissance, pays, langues et genres.
Utiliser les types appropriés pour les dates (LocalDate ou LocalDateTime en Java).

## Utilisation du projet
Clonage du repository et importation dans un IDE
Cloner le repository :

git clone <url_du_repository>
Importer le projet dans votre IDE :

- IntelliJ IDEA :

File > New > Project from Existing Sources.
Sélectionnez le dossier racine du projet cloné.
Choisissez "Import project from external model" et sélectionnez "Maven".
Suivez les instructions pour importer le projet.

- Eclipse :

File > Import > Existing Maven Projects.
Sélectionnez le dossier racine du projet cloné.
Cliquez sur Finish pour importer le projet.
Utilisation de l'application
Extraction des données depuis les fichiers CSV :


### Extraction et Persistance des Données
Pour **extraire et persister** toutes les données des fichiers CSV dans la base de données `movie_database`, suivez cette étape :

exécutez la classe ``CsvExtractionManager`` qui se trouve dans le `package readers`.


### Lancement de l'application de recherche :

Pour **lancer l'application** avec le menu permettant à l'utilisateur de réaliser différentes recherches: exécutez la classe `UserInterface` située dans le `package researchApp`.

Fonctionnalités de l'application

L'application UserInterface propose un menu interactif permettant d'effectuer les actions suivantes :

1. Affichage de la filmographie d’un acteur donné

2. Affichage du casting d’un film donné

3. Affichage des films sortis entre 2 années données

4. Affichage des films communs à 2 acteurs/actrices donnés.

5. Affichage des acteurs communs à 2 films donnés

6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting

7. Trouver le plus court chemin entre 2 acteurs (algorithme de la théorie des graphes à utiliser)

8. Fin de l’application

