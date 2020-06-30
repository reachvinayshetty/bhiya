/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collegefees.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Torus
 */
public class DBConnection {
    Connection connection;
    public Connection getConnection(){
        try {
            Class.forName(Constants.DRIVER);
            connection = DriverManager.getConnection(Constants.URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
