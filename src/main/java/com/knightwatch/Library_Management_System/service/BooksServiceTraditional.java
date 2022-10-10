package com.knightwatch.Library_Management_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.knightwatch.Library_Management_System.model.Book;
import com.knightwatch.Library_Management_System.repository.mysql.manual.BooksDaoAPI;

@Service("Service-books-mysql-traditional")
public class BooksServiceTraditional implements BooksService
{
	@Autowired
	private final BooksDaoAPI booksDaoAPI;

	public BooksServiceTraditional(@Qualifier("Repository-books-mysql-traditional") BooksDaoAPI booksDaoAPI)
	{
		this.booksDaoAPI = booksDaoAPI;
	}

	@Override
	public Book retrieveBook(int bookId) throws Exception
	{
		return booksDaoAPI.retrieveBook(bookId);
	}

	@Override
	public List<Book> retrieveAllBooks() throws Exception
	{
		return booksDaoAPI.retrieveAllBooks();
	}

	@Override
	public Book addBook(Book book) throws Exception
	{
		return booksDaoAPI.addBook(book);
	}

	@Override
	public Book updateBook(Book book) throws Exception
	{
		return booksDaoAPI.updateBook(book);
	}

	@Override
	public void deleteBook(int bookId) throws Exception
	{
		booksDaoAPI.deleteBook(bookId);
	}
}