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

public class Queries 
{
    public static List<Books> GetBooks() 
    {
        List<Books> booksList = new ArrayList<Books>();
        Connection connection = null;

        try 
        {
            // Get the database connection
            connection = DatabaseConnector.getConnection();

            // Execute a sample query
            String query = "SELECT * FROM tbl_books"; // Replace 'tbl_books' with your table name
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Populate the list with Books objects
            while (resultSet.next()) 
            {
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

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Close the connection
            DatabaseConnector.closeConnection();
        }
        return booksList;
    }

    public static void AddBook(String title, String author, String genre, String location, String date, String quantity) 
    {
        Connection connection = null;

        try 
        {
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

        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println("Adding Failed: Please enter a valid input.");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Close the connection
            try 
            {
                // Add a 2-second delay
                Thread.sleep(2000);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            DatabaseConnector.closeConnection();
        }
    }

    public static void DeleteBook(int id) 
    {
        Connection connection = null;
    
        try 
        {
            // Get the database connection
            connection = DatabaseConnector.getConnection();
    
            // Query to delete a book based on ID
            String query = "DELETE FROM tbl_books WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
    
            // Set the ID parameter
            preparedStatement.setInt(1, id);
    
            // Execute the delete operation
            int rowsAffected = preparedStatement.executeUpdate();
    
            // Provide feedback
            if (rowsAffected > 0) 
            {
                System.out.println("Book with ID " + id + " deleted successfully.");
            } 
            else 
            {
                System.out.println("No book found with ID " + id + ".");
            }

            try 
            {
                // Add a 2-second delay
                Thread.sleep(1000);
            } 
            catch (InterruptedException g) 
            {
                g.printStackTrace();
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Close the connection
            try 
            {
                // Add a 2-second delay
                Thread.sleep(2000);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            DatabaseConnector.closeConnection();
        }
    }
    
    public static List<Books> GetSearchedBooks(String searchKeyword) 
    {
        List<Books> booksList = new ArrayList<>();
        Connection connection = null;
    
        try 
        {
            // Get the database connection
            connection = DatabaseConnector.getConnection();
    
            // Use a wildcard search for the keyword in all relevant columns
            String query = "SELECT * FROM tbl_books WHERE " +
                           "title LIKE ? OR " +
                           "author LIKE ? OR " +
                           "genre LIKE ? OR " +
                           "location LIKE ? OR " +
                           "date LIKE ? OR " +
                           "quantity LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
    
            // Add wildcards to the search keyword for partial matching
            String searchPattern = "%" + searchKeyword + "%";
            for (int i = 1; i <= 6; i++) 
            {
                preparedStatement.setString(i, searchPattern);
            }
    
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
    
            // Populate the list with Books objects
            while (resultSet.next()) 
            {
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
    
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Close the connection
            DatabaseConnector.closeConnection();
        }
        return booksList;
    }
    
    public static boolean UpdateBook(Books updatedBook) 
    {
        Connection connection = null;
        boolean isUpdated = false;
    
        System.out.println("Updating book with ID " + updatedBook.getId() + "...");
        try 
        {
            // Add a 2-second delay
            Thread.sleep(1000);
        } 
        catch (InterruptedException g) 
        {
            g.printStackTrace();
        }
        
        try 
        {
            // Get the database connection
            connection = DatabaseConnector.getConnection();
    
            // Prepare the SQL update query
            String query = "UPDATE tbl_books SET title = ?, author = ?, genre = ?, location = ?, date = ?, quantity = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
    
            // Set the parameters for the query
            preparedStatement.setString(1, updatedBook.getTitle());
            preparedStatement.setString(2, updatedBook.getAuthor());
            preparedStatement.setString(3, updatedBook.getGenre());
            preparedStatement.setString(4, updatedBook.getlocation());
            preparedStatement.setDate(5, new java.sql.Date(updatedBook.getDate().getTime()));
            preparedStatement.setInt(6, updatedBook.getQuantity());
            preparedStatement.setInt(7, updatedBook.getId());
    
            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
            isUpdated = rowsAffected > 0;
    
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Close the connectio
            
            DatabaseConnector.closeConnection();
        }
        
        return isUpdated;
    }
    

    public static boolean AddBorrower(String firstName, String middleName, String lastName, int bookId, Date dateBorrowed) 
    {
        Connection connection = null;
        boolean isAdded = false;
    
        try 
        {
            // Get the database connection
            connection = DatabaseConnector.getConnection();
    
            // Start transaction
            connection.setAutoCommit(false);
    
            // Check if the book quantity is greater than 0
            String checkQuery = "SELECT quantity FROM tbl_books WHERE id = ? AND quantity > 0";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, bookId);
            ResultSet resultSet = checkStatement.executeQuery();
    
            if (resultSet.next()) 
            {
                int quantity = resultSet.getInt("quantity");
    
                // Insert borrower details into tbl_borrower
                String insertQuery = "INSERT INTO tbl_borrower (first_name, middle_name, last_name, book_id, date_borrowed) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
    
                insertStatement.setString(1, firstName);
                insertStatement.setString(2, middleName);
                insertStatement.setString(3, lastName);
                insertStatement.setInt(4, bookId);
                insertStatement.setDate(5, new java.sql.Date(dateBorrowed.getTime()));
    
                int rowsInserted = insertStatement.executeUpdate();
    
                // Update the quantity in tbl_books
                if (rowsInserted > 0) 
                {
                    String updateQuery = "UPDATE tbl_books SET quantity = ? - 1 WHERE id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setInt(1, quantity);
                    updateStatement.setInt(2, bookId);
    
                    int rowsUpdated = updateStatement.executeUpdate();
    
                    if (rowsUpdated > 0) 
                    {
                        isAdded = true;
                    }
                }
    
                // Commit transaction
                connection.commit();
            } 
    
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            try 
            {
                if (connection != null) 
                {
                    connection.rollback();
                }
            } 
            catch (SQLException rollbackEx) 
            {
                rollbackEx.printStackTrace();
            }
        } 
        finally 
        {
            // Close the connection
            DatabaseConnector.closeConnection();
        }
    
        return isAdded;
    }
    
    public static List<Borrower> GetBorrowers() 
    {
        List<Borrower> borrowers = new ArrayList<>();
        Connection connection = null;

        try 
        {
            // Get the database connection
            connection = DatabaseConnector.getConnection();

            // Query to get all borrowers
            String query = "SELECT id, first_name, middle_name, last_name, book_id, date_borrowed FROM tbl_borrower";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Loop through the result set and populate the Borrower list
            while (resultSet.next()) 
            {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String middleName = resultSet.getString("middle_name");
                String lastName = resultSet.getString("last_name");
                int bookId = resultSet.getInt("book_id");
                Date dateBorrowed = resultSet.getDate("date_borrowed");

                Borrower borrower = new Borrower(id, firstName, middleName, lastName, bookId, dateBorrowed);
                borrowers.add(borrower);
            }

            resultSet.close();
            statement.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Close the connection
            DatabaseConnector.closeConnection();
        }
        borrowers = AddTitle(borrowers);
        return borrowers;
    }

    public static boolean ReturnBook(Borrower borrower) 
    {
        Connection connection = null;
        boolean isReturned = false;
    
        int bookId = borrower.getBookId();
        int borrowerId = borrower.getId(); // Assuming there's a method to get the borrower's ID
        try 
        {
            // Get the database connection
            connection = DatabaseConnector.getConnection();
    
            // Start transaction
            connection.setAutoCommit(false);
    
            // Check if the book exists in the tbl_books table
            String checkQuery = "SELECT quantity FROM tbl_books WHERE id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, bookId);
            ResultSet resultSet = checkStatement.executeQuery();
    
            if (resultSet.next()) 
            {
                int quantity = resultSet.getInt("quantity");
    
                // Delete the borrower from the tbl_borrower table
                String deleteQuery = "DELETE FROM tbl_borrower WHERE id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, borrowerId);
                int rowsDeleted = deleteStatement.executeUpdate();
    
                // Update the book quantity in tbl_books if the borrower is deleted
                if (rowsDeleted > 0) 
                {
                    String updateQuery = "UPDATE tbl_books SET quantity = ? + 1 WHERE id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setInt(1, quantity); // Increase the quantity by 1
                    updateStatement.setInt(2, bookId);
    
                    int rowsUpdated = updateStatement.executeUpdate();
    
                    if (rowsUpdated > 0) 
                    {
                        isReturned = true;
                    }
                }
            }
    
            // Commit transaction
            connection.commit();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            try 
            {
                if (connection != null) 
                {
                    connection.rollback();
                }
            } 
            catch (SQLException rollbackEx) 
            {
                rollbackEx.printStackTrace();
            }
        } 
        finally 
        {
            // Close the connection
            DatabaseConnector.closeConnection();
        }
    
        return isReturned;
    }
    

    public static int CountBooks() 
    {
        Connection connection = null;
        int count = 0;
    
        try 
        {
            // Get the database connection
            connection = DatabaseConnector.getConnection();
    
            // Query to count the number of books in tbl_books
            String query = "SELECT COUNT(*) FROM tbl_books";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
    
            // Get the count from the result set
            if (resultSet.next()) 
            {
                count = resultSet.getInt(1); // The count is the first column in the result
            }
    
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Close the connection
            DatabaseConnector.closeConnection();
        }
        return count;
    }
    public static int CountBorrowedBooks() 
    {
        Connection connection = null;
        int count = 0;
    
        try 
        {
            // Get the database connection
            connection = DatabaseConnector.getConnection();
    
            // Query to count the number of books in tbl_books
            String query = "SELECT COUNT(*) FROM tbl_borrower";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
    
            // Get the count from the result set
            if (resultSet.next()) 
            {
                count = resultSet.getInt(1); // The count is the first column in the result
            }
    
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Close the connection
            DatabaseConnector.closeConnection();
        }
    
        return count;
    }
    
    private static List<Borrower> AddTitle(List<Borrower> borrowers) 
    {
        List<Borrower> newBorrowers = new ArrayList<>();
        Connection connection = null;
    
        try 
        {
            // Get the database connection
            connection = DatabaseConnector.getConnection();
    
            // Query to get the title of the book borrowed by each borrower
            String query = "SELECT title FROM tbl_books WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
    
            for (Borrower borrower : borrowers) 
            {
                statement.setInt(1, borrower.getBookId());
                ResultSet resultSet = statement.executeQuery();
    
                if (resultSet.next()) 
                {
                    String title = resultSet.getString("title");

                    Borrower borrower2 = new Borrower(borrower.getId(), borrower.getFName(), borrower.getMName(), borrower.getLName(), borrower.getBookId(), borrower.getDateBorrowed(), title);
                    newBorrowers.add(borrower2);
                }
            }
    
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return newBorrowers;
    }

}
