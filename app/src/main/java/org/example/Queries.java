package org.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeParseException;
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

    public static void AddBook(String title, String author, String genre, String location, String date, String quantity) {
        Connection connection = null;

        try {
            // Get the database connection
            connection = DatabaseConnector.getConnection();

                // Convert `date` to `java.sql.Date`
            Date sqlDate = Date.valueOf(date); // Input date must be in "yyyy-MM-dd" format

            // Convert `quantity` to `int`
            int bookQuantity = Integer.parseInt(quantity);
            // Query to insert a book
            String query = "INSERT INTO tbl_books (title, author, genre, location, date, quantity) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set parameters
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, author);
        preparedStatement.setString(3, genre);
        preparedStatement.setString(4, location);
        preparedStatement.setDate(5, sqlDate);
        preparedStatement.setInt(6, bookQuantity);

            // Execute update
            preparedStatement.executeUpdate();

        } catch (IllegalArgumentException e) {
            System.out.println("Adding Failed: Please enter a valid input.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                // Add a 2-second delay
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            DatabaseConnector.closeConnection();
        }
    }

}
