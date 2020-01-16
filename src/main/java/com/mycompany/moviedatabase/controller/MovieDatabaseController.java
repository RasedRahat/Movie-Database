/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.controller;

import com.mycompany.moviedatabase.dao.MovieDatabaseDao;
import com.mycompany.moviedatabase.dao.MovieDatabaseDaoFileImpl;
import com.mycompany.moviedatabase.dao.MovieDatabaseDaoException;
import com.mycompany.moviedatabase.dto.Movie;
import com.mycompany.moviedatabase.ui.MovieDatabaseView;
import com.mycompany.moviedatabase.ui.UserIO;
import com.mycompany.moviedatabase.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Dev10
 */
public class MovieDatabaseController {

    MovieDatabaseView view;
    MovieDatabaseDao dao;
   // private UserIO io = new UserIOConsoleImpl();

    public MovieDatabaseController(MovieDatabaseDao dao, MovieDatabaseView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    createMovie();
                    break;
                case 2:
                    removeMovie();
                    break;
                case 3:
                    editMovie();
                    break;
                case 4:
                    listMovies();
                    break;
                case 5:
                    viewMovie();
                    break;
                case 6:
                    searchMovie();
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createMovie() {
        view.displayCreateMovieBanner();
        Movie newMovie = view.getNewMovieInfo();
        try {
            dao.addMovie(newMovie);
            view.displayCreateSuccessBanner();
        } catch (MovieDatabaseDaoException ex) {
            view.displayCreateFailureBanner();
        }
    }

    private void listMovies() {
        view.displayDisplayAllBanner();
        try {
            List<Movie> movieList = dao.getAllMovie();
            view.displayMovieList(movieList);
        } catch (MovieDatabaseDaoException ex) {
            view.displaySearchMovieFailure();
        }
    }

    private void viewMovie() {
        view.displayDisplayStudentBanner();
        int id = view.getIdChoice();
        try {
            Movie movie = dao.getMovie(id);
            view.displayMovie(movie);
        } catch (MovieDatabaseDaoException ex) {
            view.displayIdNotFound();
        }
    }

    private void searchMovie() {
        String title = view.getTitleChoice();
        try {
            List<Movie> movies = dao.getMoviesByTitle(title);
            view.displayMovieList(movies);
////            for (Movie movie : movies) {
////                view.displayMovie(movie);
//            }
        } catch (MovieDatabaseDaoException ex) {
            view.displaySearchMovieFailure();
        }
    }

    private void editMovie() {
        int id = view.getIdChoice();
        try {
            Movie movie = dao.getMovie(id);
            movie = view.edit(movie);
            dao.editMovie(id, movie);
            view.displayEditSuccess();
        } catch (MovieDatabaseDaoException ex) {
            view.displayIdNotFound();
        }
    }

    private void removeMovie() {
        view.displayRemoveStudentBanner();
        int id = view.getIdChoice();
        try {
            dao.removeMovie(id);
            view.displayRemoveSuccessBanner();
        } catch (MovieDatabaseDaoException ex) {
            view.displayIdNotFound();
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
