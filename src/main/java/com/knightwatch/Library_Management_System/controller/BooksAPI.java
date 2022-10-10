package com.knightwatch.Library_Management_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.knightwatch.Library_Management_System.model.APIResponse;
import com.knightwatch.Library_Management_System.model.Book;
import com.knightwatch.Library_Management_System.service.BooksService;

@RestController
//@RestController - Combines both functionality of @Controller & @ResponseBody
public class BooksAPI
{
	private final BooksService booksService;

//	@Autowired
//	public BooksAPI(@Qualifier("Service-books-mysql-orm-jpa") BooksService booksService)
//	{
//		this.booksService = booksService;
//	}

	@Autowired
	public BooksAPI(@Qualifier("Service-books-mysql-traditional") BooksService booksService)
	{
		this.booksService = booksService;
	}

	@PostMapping("api/v1/books")
	public ResponseEntity<APIResponse> addBook(@RequestBody Book book) throws Exception
	{
		Book bookResponse = booksService.addBook(book);
		return new APIResponse.Builder()
			.setHttpStatusCode(HttpStatus.OK.value())
			.setMessage("Book was added successfully")
			.setEntityData(bookResponse)
			.build();
	}

	@PutMapping(path = "api/v1/books/{bookId}")
	public ResponseEntity<APIResponse> updateBook(@PathVariable("bookId") int bookIdToUpdate, @RequestBody Book bookToBeUpdated) throws Exception
	{
		Book bookFromDB = booksService.retrieveBook(bookIdToUpdate);

		Integer bookId = bookFromDB.getBookId();
		String bookTitle = bookToBeUpdated.getBookTitle() == null ? bookFromDB.getBookTitle() : bookToBeUpdated.getBookTitle();
		String authorName = bookToBeUpdated.getAuthorName() == null ? bookFromDB.getAuthorName() : bookToBeUpdated.getAuthorName();
		String genre = bookToBeUpdated.getGenre() == null ? bookFromDB.getGenre() : bookToBeUpdated.getGenre();
		String isbn = bookToBeUpdated.getIsbn() == null ? bookFromDB.getIsbn() : bookToBeUpdated.getIsbn();
		String publishedYear = bookToBeUpdated.getPublishedYear() == null ? bookFromDB.getPublishedYear() : bookToBeUpdated.getPublishedYear();
		Integer stock = bookToBeUpdated.getStock() == -1 ? bookFromDB.getStock() : bookToBeUpdated.getStock();

		Book bookToPersist = new Book.Builder()
			.setBookId(bookId)
			.setBookTitle(bookTitle)
			.setAuthorName(authorName)
			.setISBN(isbn)
			.setStock(stock)
			.setPublishedYear(publishedYear)
			.setGenre(genre)
			.build();

		Book updatedBook = booksService.updateBook(bookToPersist);
		return new APIResponse.Builder<>()
			.setHttpStatusCode(HttpStatus.OK.value())
			.setMessage("Book updated successfully")
			.setEntityData(updatedBook)
			.build();
	}

	@DeleteMapping(path = "api/v1/books/{bookId}")
	public ResponseEntity<APIResponse> deleteBook(@PathVariable("bookId") int bookId) throws Exception
	{
		Book bookToDelete = booksService.retrieveBook(bookId);
		booksService.deleteBook(bookId);
		return new APIResponse.Builder<>()
			.setHttpStatusCode(HttpStatus.OK.value())
			.setMessage("Book deleted successfully")
			.build();
	}

	@GetMapping(path = "api/v1/books/{bookId}")
	public ResponseEntity retrieveBook(@PathVariable("bookId") int bookId) throws Exception
	{
		Book result = booksService.retrieveBook(bookId);

		return new APIResponse.Builder<>()
			.setHttpStatusCode(HttpStatus.OK.value())
			.setMessage("Book retrieved successfully")
			.setEntityData(result)
			.build();
	}

	@GetMapping("api/v1/books")
	public ResponseEntity retrieveAllBooks() throws Exception
	{
		List<Book> result = booksService.retrieveAllBooks();
		return new APIResponse.Builder()
			.setHttpStatusCode(HttpStatus.OK.value())
			.setMessage("All books retrived successfully")
			.setEntityList(result)
			.build();
	}

}
