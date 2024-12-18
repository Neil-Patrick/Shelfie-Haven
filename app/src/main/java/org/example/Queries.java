package org.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class Queries {
    public static List<Books> GetBooks() {
        List<Books> booksList = new ArrayList<Books>();
        Connection connection = null;

    try {
        // Get the database connection
        connection = DatabaseConnector.getConnection();

        // Execute a sample query
        String query = "SELECT * FROM tbl_books"; // Replace 'tbl_books' with your table name
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Populate the list with Books objects
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String genre = resultSet.getString("genre");
            String location = resultSet.getString("location");
            Date date = resultSet.getDate("date");
            int quantity = resultSet.getInt("quantity");

            // Create a Books object and add it to the list
            Books book = new Books(id, title, author, genre, location, date, quantity);
            booksList.add(book);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the connection
        DatabaseConnector.closeConnection();
    }
        return booksList;
    }
}
