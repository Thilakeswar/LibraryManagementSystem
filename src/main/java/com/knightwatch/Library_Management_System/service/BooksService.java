package com.knightwatch.Library_Management_System.service;

import java.util.List;

import com.knightwatch.Library_Management_System.model.Book;

public interface BooksService
{
	public Book retrieveBook(int bookId) throws Exception;

	public List<Book> retrieveAllBooks() throws Exception;

	public Book addBook(Book book) throws Exception;

	public Book updateBook(Book book) throws Exception;

	public void deleteBook(int bookId) throws Exception;
}