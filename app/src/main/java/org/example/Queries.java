package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
    public static void GetAllBooks() {
        Connection connection = null;

        try {
            // Get the database connection
            connection = DatabaseConnector.getConnection();

            // Execute a sample query
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tbl_books"; // Replace 'yourTableName'
            ResultSet resultSet = statement.executeQuery(query);

            // Print the results
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id")); // Replace 'id' with your column name
                System.out.println("Name: " + resultSet.getString("title")); // Replace 'name'
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            DatabaseConnector.closeConnection();
        }
    }
}
