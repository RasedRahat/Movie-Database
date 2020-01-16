/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.ui;

import com.mycompany.moviedatabase.dto.Movie;
import java.util.List;

/**
 *
 * @author Dev10
 */
public class MovieDatabaseView {

    private UserIO io;

    public MovieDatabaseView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("***************************************************************");
        io.print("1. Add a movie to the database");
        io.print("2. Remove a movie from the database");
        io.print("3. Edit the information for an existing Movie in the database");
        io.print("4. List all Movies in the database");
        io.print("5. Display the information for a single Movie");
        io.print("6. Search for a Movie by title");
        io.print("7. Exit");
        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public Movie getNewMovieInfo() {
//        int id = io.readInt("Please enter movie id");
        String title = io.readString("Please enter movie title");
        String releaseDate = io.readString("Please enter release date");
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String directorsName = io.readString("Please enter director's name");
        String studio = io.readString("Please enter studio name");
        String userRating = io.readString("Please enter user rating");
        Movie currentMovie = new Movie();
        currentMovie.setTitle(title);
        currentMovie.setReleaseDate(releaseDate);
        currentMovie.setMpaaRating(mpaaRating);
        currentMovie.setDirectorsName(directorsName);
        currentMovie.setStudio(studio);
        currentMovie.setUserRating(userRating);
        return currentMovie;
    }

    public void displayCreateMovieBanner() {
        io.print("===Create Movie==");
    }

    public void displayCreateSuccessBanner() {
        io.print("Movie successfully created. Please hit enter to continue.");
    }
    
    public void displayCreateFailureBanner() {
        io.print("Failed to create movie. Please try again.");
    }

    public void displayMovieList(List<Movie> movieList) {
        for (Movie currentMovie : movieList) {
            io.print( currentMovie.getId() + ": " 
                    + currentMovie.getTitle() + ": "
                    + currentMovie.getReleaseDate() + " "
                    + currentMovie.getMpaaRating() + " "
                    + currentMovie.getDirectorsName() + " "
                    + currentMovie.getStudio() + " "
                    + currentMovie.getUserRating());
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("===Display All Movies===");
    }

    public void displayDisplayStudentBanner() {
        io.print("===Display Movie===");
    }

    public int getIdChoice() {
        return io.readInt("Please enter the movie id.");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the movie title.");
    }

    public void displayMovie(Movie movie) {
        if (movie != null) {
            io.print(movie.getTitle() + " "
                    + movie.getReleaseDate() + " "
                    + movie.getMpaaRating() + " "
                    + movie.getDirectorsName() + " "
                    + movie.getStudio() + " "
                    + movie.getUserRating());
            io.print("");
        } else {
            io.print("No such Movie.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveStudentBanner() {
        io.print("===Remove Movie===");
    }

    public void displayRemoveSuccessBanner() {
        io.print("Movie successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }

    public void displayIdNotFound() {
        io.print("There is no movie with that id in the database!");
    }
    
    public void displaySearchMovieFailure() {
        io.print("Failed to search movies.");
    }

    public Movie edit(Movie movie) {
        String title = io.readString(String.format("Title (%s):", movie.getTitle()));
        if (!title.isEmpty()) {
            movie.setTitle(title);
        }

        String releaseDate = io.readString(String.format("Release Date (%s):", movie.getReleaseDate()));
        if (!releaseDate.isEmpty()) {
            movie.setReleaseDate(releaseDate);
        }

        String mpaaRating = io.readString(String.format("MPAA Rating (%s):", movie.getMpaaRating()));
        if (!mpaaRating.isEmpty()) {
            movie.setMpaaRating(mpaaRating);
        }

        String directorsName = io.readString(String.format("Director's Name (%s):", movie.getDirectorsName()));
        if (!directorsName.isEmpty()) {
            movie.setDirectorsName(directorsName);
        }

        String studio = io.readString(String.format("Studio (%s):", movie.getStudio()));
        if (!studio.isEmpty()) {
            movie.setStudio(studio);
        }

        String userRating = io.readString(String.format("User Rating (%s):", movie.getUserRating()));
        if (!userRating.isEmpty()) {
            movie.setUserRating(userRating);
        }

        return movie;
    }

    public void displayEditSuccess() {
       io.print("Movie information successfully edited.");
    }
}
