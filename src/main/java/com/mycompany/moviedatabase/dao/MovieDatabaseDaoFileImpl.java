/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.dao;

import com.mycompany.moviedatabase.dto.Movie;
import com.mycompany.moviedatabase.dao.MovieDatabaseDaoException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dev10
 */
public class MovieDatabaseDaoFileImpl implements MovieDatabaseDao {

    private static final String FILE_PATH = "movies";
    private static final String DELIMITER = "::";

    @Override
    public Movie addMovie(Movie movie) throws MovieDatabaseDaoException {
        int id = 0;
        List<Movie> all = getAllMovie();
        for (Movie m : all) {
            id = Math.max(id, m.getId());
        }
        movie.setId(id + 1);
        all.add(movie);
        save(all);
        return movie;
    }

    @Override
    public List<Movie> getAllMovie() throws MovieDatabaseDaoException {
        ArrayList<Movie> all = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Movie movie = unmarshall(line);
                if (movie != null) {
                    all.add(movie);
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            throw new MovieDatabaseDaoException(ex.getMessage(), ex);
        }
        return all;
    }

    @Override
    public Movie getMovie(int id) throws MovieDatabaseDaoException {
        for (Movie movie : getAllMovie()) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        throw new MovieDatabaseDaoException("Movie not found");
    }

    @Override
    public List<Movie> getMoviesByTitle(String title) throws MovieDatabaseDaoException {
        List<Movie> moviesWithTitle = new ArrayList<>();
        for (Movie movie : getAllMovie()) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                moviesWithTitle.add(movie);
            }
        }
        return moviesWithTitle;
    }

    @Override
    public Movie removeMovie(int id) throws MovieDatabaseDaoException {
        List<Movie> all = getAllMovie();
        for (Movie movie : all) {
            if (movie.getId() == id) {
                all.remove(movie);
                save(all);
                return movie;
            }
        }
        return null;
    }

    private void save(List<Movie> movies) throws MovieDatabaseDaoException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Movie movie : movies) {
                writer.println(marshall(movie));
            }
        } catch (IOException ex) {
            throw new MovieDatabaseDaoException(ex.getMessage(), ex);
        }
    }

    private String marshall(Movie movie) {
        return movie.getId() + DELIMITER
                + movie.getTitle() + DELIMITER
                + movie.getReleaseDate() + DELIMITER
                + movie.getMpaaRating() + DELIMITER
                + movie.getDirectorsName() + DELIMITER
                + movie.getStudio() + DELIMITER
                + movie.getUserRating();
    }

    private Movie unmarshall(String line) {

        String[] tokens = line.split(DELIMITER);
        if (tokens.length != 7) {
            return null;
        }

        Movie movie = new Movie(Integer.parseInt(tokens[0]));
        movie.setTitle(tokens[1]);
        movie.setReleaseDate(tokens[2]);
        movie.setMpaaRating(tokens[3]);
        movie.setDirectorsName(tokens[4]);
        movie.setStudio(tokens[5]);
        movie.setUserRating(tokens[6]);
        return movie;
    }

    @Override
    public Movie editMovie(int id, Movie editMovie) throws MovieDatabaseDaoException {
        List<Movie> all = getAllMovie();
        for (int i = 0; i < all.size(); i ++) {
              Movie currentMovie = all.get(i);
            if (currentMovie.getId() == id) {
                all.set(i, editMovie);
                save(all);
                editMovie = currentMovie; 
            }
        } 
        return editMovie;
    }
}
