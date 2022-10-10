package com.knightwatch.Library_Management_System.repository.mysql.manual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.knightwatch.Library_Management_System.model.Book;

@Repository("Repository-books-mysql-traditional")
public class BooksDaoAPI
{
	public static Logger logger = LogManager.getLogger(BooksDaoAPI.class);

	public Book retrieveBook(int bookID) throws Exception
	{
		String queryStr = "SELECT * from Books where BookId = ?";
		Book book = null;
		try(
			Connection con = MySQLDataSource.getNewDBConnection();
			PreparedStatement preparedStatement = con.prepareStatement(queryStr);
		)
		{
			preparedStatement.setInt(1, bookID);

			ResultSet resultSet = preparedStatement.executeQuery();
			List<Book> books = BooksDaoUtil.getAsBooks(resultSet);
			if(books.size() == 0)
			{
				logger.info("Book not found. Please enter proper BookID");
				throw new Exception("Book not found. Please enter proper BookID");
			}
			book = books.get(0);
		}
		catch(SQLException e)
		{
			logger.error("Exception in establishing MySQL connection due to ", e);
			throw new Exception("Error in retrieving book details. Please try again!");
		}
		return book;
	}

	public ArrayList<Book> retrieveAllBooks() throws Exception
	{
		String queryStr = "SELECT * from Books";
		ArrayList<Book> books = null;
		try(
			Connection con = MySQLDataSource.getNewDBConnection();
			PreparedStatement preparedStatement = con.prepareStatement(queryStr);
		)
		{
			ResultSet resultSet = preparedStatement.executeQuery();
			books = BooksDaoUtil.getAsBooks(resultSet);
		}
		catch(SQLException e)
		{
			logger.error("Exception in establishing MySQL connection due to ", e);
			throw new Exception("Error in retrieving book details. Please try again!");
		}
		return books;
	}

	public Book addBook(Book book) throws Exception
	{
		String queryStr = "INSERT INTO Books(BookTitle, AuthorName, Genre, CurrentStock, PublishedYear, ISBN) VALUES (?,?,?,?,?,?)";
		try(
			Connection con = MySQLDataSource.getNewDBConnection();
			PreparedStatement preparedStatement = con.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
		)
		{
			preparedStatement.setString(1, book.getBookTitle());
			preparedStatement.setString(2, book.getAuthorName());
			preparedStatement.setString(3, book.getGenre());
			preparedStatement.setInt(4, book.getStock());
			preparedStatement.setString(5, book.getPublishedYear());
			preparedStatement.setString(6, book.getIsbn());

			//Executing the query
			int result = preparedStatement.executeUpdate();
			if(result == 0)
			{
				logger.error("Creating book failed, no rows affected.");
				throw new Exception("Creating book failed. Please try again!");
			}

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			Integer bookId = null;
			while(resultSet.next())
			{
				bookId = resultSet.getInt(1);
			}
			return retrieveBook(bookId);
		}
		catch(SQLException e)
		{
			logger.error("Exception in establishing MySQL connection due to ", e);
			throw new Exception("Error in creating book details. Please try again!");
		}
	}

	public Book updateBook(Book book) throws Exception
	{
		String queryStr = "UPDATE Books SET BookTitle = ?, AuthorName = ?, Genre = ?, CurrentStock = ?, PublishedYear = ?, ISBN = ? WHERE BookId = ?";
		try(
			Connection con = MySQLDataSource.getNewDBConnection();
			PreparedStatement preparedStatement = con.prepareStatement(queryStr);
		)
		{
			preparedStatement.setString(1, book.getBookTitle());
			preparedStatement.setString(2, book.getAuthorName());
			preparedStatement.setString(3, book.getGenre());
			preparedStatement.setInt(4, book.getStock());
			preparedStatement.setString(5, book.getPublishedYear());
			preparedStatement.setString(6, book.getIsbn());
			preparedStatement.setInt(7, book.getBookId());

			//Executing the query
			int result = preparedStatement.executeUpdate();
			if(result == 0)
			{
				logger.error("Updating book failed, no rows affected.");
				throw new Exception("Updation of book failed. Please try again!");
			}

			return retrieveBook(book.getBookId());
		}
		catch(SQLException e)
		{
			logger.error("Exception in establishing MySQL connection due to ", e);
			throw new Exception("Error in updating book details. Please try again!");
		}
	}

	public void deleteBook(int bookID) throws Exception
	{
		String queryStr = "DELETE FROM Books where BookId = ?";
		try(
			Connection con = MySQLDataSource.getNewDBConnection();
			PreparedStatement preparedStatement = con.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
		)
		{
			preparedStatement.setInt(1, bookID);
			int result = preparedStatement.executeUpdate();
			if(result == 0)
			{
				logger.error("Deletion of book failed, no rows affected.");
				throw new Exception("Deletion of book failed. Please try again!");
			}
		}
		catch(SQLException e)
		{
			logger.error("Exception in establishing MySQL connection due to ", e);
			throw new Exception("Error in deleting book details. Please try again!");
		}
	}
}
