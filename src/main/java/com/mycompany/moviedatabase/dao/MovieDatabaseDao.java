/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.dao;

import com.mycompany.moviedatabase.dto.Movie;
import java.util.List;

/**
 *
 * @author Dev10
 */
public interface MovieDatabaseDao {

    Movie addMovie(Movie movie) throws MovieDatabaseDaoException;

    List<Movie> getAllMovie() throws MovieDatabaseDaoException;

    Movie getMovie(int id) throws MovieDatabaseDaoException;

    List<Movie> getMoviesByTitle(String title) throws MovieDatabaseDaoException;

    Movie removeMovie(int id) throws MovieDatabaseDaoException;
    
    Movie editMovie(int id, Movie editMovie) throws MovieDatabaseDaoException;
}
