package com.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
  private static final String URL = "jdbc:mysql://localhost:3306/coursejdbc";
  private static final String USER = "root";
  private static final String PASSWORD = "mysql";

  private static Connection connection = null;

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (SQLException e) {
        throw new DBException(e.getMessage());
      }
    } 
    
    return connection;
  }

  public static void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new DBException(e.getMessage());
      }
    }
  }

  public static void closeStatement(Statement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        throw new DBException(e.getMessage());
      }
    }
  }

  public static void closeResultSet(ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        throw new DBException(e.getMessage());
      }
    }
  }
}
