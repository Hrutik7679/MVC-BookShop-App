package com.apponix.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class BookDao   {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cdac";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

   
    public BookDao() throws SQLException {
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public int save(Book b) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, b.getId());
            preparedStatement.setString(2, b.getName());
            preparedStatement.setString(3, b.getAuthor());
            preparedStatement.setString(4, b.getSubject());
            preparedStatement.setDouble(5, b.getPrice());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

   
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String Name = resultSet.getString("Name");
                String author = resultSet.getString("author");
                String subject = resultSet.getString("subject");
                double price = resultSet.getDouble("price");

                Book book = new Book(id, Name, author, subject, price);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }
    
    public int update(Book b) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE books SET Name=?, author=?, subject=?, price=? WHERE id=?")) {

            preparedStatement.setString(1, b.getName());
            preparedStatement.setString(2, b.getAuthor());
            preparedStatement.setString(3, b.getSubject());
            preparedStatement.setDouble(4, b.getPrice());
            preparedStatement.setInt(5, b.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteById(int bookId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE id=?")) {

            preparedStatement.setInt(1, bookId);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Book> findBySubject(String subject) {
        List<Book> bookList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE subject=?")) {

            preparedStatement.setString(1, subject);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String Name = resultSet.getString("Name");
                    String author = resultSet.getString("author");
                    double price = resultSet.getDouble("price");

                    Book book = new Book(id, Name, author, subject, price);
                    bookList.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> bookList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE author=?")) {

            preparedStatement.setString(1, author);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String Name = resultSet.getString("Name");
                    String subject = resultSet.getString("subject");
                    double price = resultSet.getDouble("price");

                    Book book = new Book(id, Name, author, subject, price);
                    bookList.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    public Book findById(int bookId) {
        Book book = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE id=?")) {

            preparedStatement.setInt(1, bookId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String Name = resultSet.getString("Name");
                    String author = resultSet.getString("author");
                    String subject = resultSet.getString("subject");
                    double price = resultSet.getDouble("price");

                    book = new Book(bookId, Name, author, subject, price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }


    public Set<String> findAllSubjects() {
        Set<String> subjects = new LinkedHashSet<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT subject FROM books");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                subjects.add(resultSet.getString("subject"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    
}
