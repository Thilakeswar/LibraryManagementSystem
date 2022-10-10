package com.knightwatch.Library_Management_System.repository.mysql.manual;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.knightwatch.Library_Management_System.model.Book;

public class BooksDaoUtil
{
	public static ArrayList<Book> getAsBooks(ResultSet resultSet) throws SQLException
	{
		ArrayList<Book> books = new ArrayList<Book>();
		while(resultSet.next())
		{
			int bookId = resultSet.getInt("BookId");
			String bookTitle = resultSet.getString("BookTitle");
			String authorName = resultSet.getString("AuthorName");
			String genre = resultSet.getString("Genre");
			int currentStock = resultSet.getInt("CurrentStock");
			String publishedYear = resultSet.getString("PublishedYear");
			String isbn = resultSet.getString("ISBN");
			Book book = new Book.Builder()
				.setBookId(bookId)
				.setBookTitle(bookTitle)
				.setISBN(isbn)
				.setStock(currentStock)
				.setAuthorName(authorName)
				.setPublishedYear(publishedYear)
				.setGenre(genre)
				.build();
			books.add(book);
		}
		return books;
	}
}
