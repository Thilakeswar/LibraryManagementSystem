package com.knightwatch.Library_Management_System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.knightwatch.Library_Management_System.model.Book;
import com.knightwatch.Library_Management_System.repository.mysql.orm.jpa.BooksJPARepository;

@Service("Service-books-mysql-orm-jpa")
public class BooksServiceUsingJPAORM implements BooksService
{
	@Autowired
	private final BooksJPARepository booksJPARepository;

	public BooksServiceUsingJPAORM(@Qualifier("Repository-books-mysql-orm-jpa") BooksJPARepository booksJPARepository)
	{
		this.booksJPARepository = booksJPARepository;
	}

	@Override public Book retrieveBook(int bookId) throws Exception
	{
		Optional<Book> optional = booksJPARepository.findById(bookId);
		if(!optional.isPresent())
		{
			throw new Exception("Specified book does not exist");
		}
		return optional.get();
	}

	@Override public List<Book> retrieveAllBooks() throws Exception
	{
		return booksJPARepository.findAll();
	}

	@Override public Book addBook(Book book) throws Exception
	{
		return booksJPARepository.save(book);
	}

	@Override public Book updateBook(Book book) throws Exception
	{
		return booksJPARepository.save(book);
	}

	@Override public void deleteBook(int bookId) throws Exception
	{
		retrieveBook(bookId);
		booksJPARepository.deleteById(bookId);
	}
}
