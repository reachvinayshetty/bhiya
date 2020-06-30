/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collegefees.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Torus
 */
public class DBImplementation implements DAO{
    Connection connection;
    Statement statement;
    DBConnection dBConnection;
    
    public DBImplementation() {
        try{
            dBConnection = new DBConnection();
            connection = dBConnection.getConnection();
            if(connection!=null){
                statement = connection.createStatement();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public int putData(String query) {
        try {
            if(statement!=null){
                int rows = statement.executeUpdate(query);
                return rows;
            }
            else{
                return -1;
            }
        } catch (Exception e) {
            System.err.println("Exception in putData");
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ResultSet getData(String query) {
        try {
            if(statement!=null){
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet;
            }
            else{
                return null;
            }
        } catch (Exception e) {
            System.err.println("Exception in getData");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isExists(String query) {
        try {
            if(statement!=null){
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet!=null){
                    if(resultSet.next()){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                return true;
            }
            else{
                return true;
            }
        } catch (Exception e) {
            System.err.println("Exception in isExists");
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public void closeConnection() {
        try {
            if(statement != null){
                statement.close();
                connection.close();
            }
        } catch (Exception e) {
            System.err.println("Exception in closeConnection");
            e.printStackTrace();
        }
    }

	@Override
	public int getPrimaryKey(String primaryKey, String table) {
		// TODO Auto-generated method stub
		int id = 0;
		String query = "select "+primaryKey+" from "+table+" order by "+primaryKey+" desc limit 1";
		try {
            if(statement!=null){
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet!=null){
                    if(resultSet.next()){
                        id = resultSet.getInt(primaryKey);
                    }
                    
                }
            }
        } catch (Exception e) {
            System.err.println("Exception in isExists");
            e.printStackTrace();
        }
		return id;
	}
    
}
