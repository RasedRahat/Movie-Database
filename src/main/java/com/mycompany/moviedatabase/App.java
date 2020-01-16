/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase;

import com.mycompany.moviedatabase.controller.MovieDatabaseController;
import com.mycompany.moviedatabase.dao.MovieDatabaseDao;
import com.mycompany.moviedatabase.dao.MovieDatabaseDaoFileImpl;
import com.mycompany.moviedatabase.ui.MovieDatabaseView;
import com.mycompany.moviedatabase.ui.UserIO;
import com.mycompany.moviedatabase.ui.UserIOConsoleImpl;

/**
 *
 * @author Rasedul Hossain Rahat
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        MovieDatabaseView myView = new MovieDatabaseView(myIo);
        MovieDatabaseDao myDao = new MovieDatabaseDaoFileImpl();
        MovieDatabaseController controller = new MovieDatabaseController(myDao, myView);
        controller.run();
    }
}
