package com.knightwatch.Library_Management_System.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@Entity
@Table(name = "books")
//@Builder - Use this when you want to automatically create Builder functions
@JsonDeserialize(builder = Book.Builder.class)
public class Book
{
	@Id
	@Column(name = "bookid")
	//Setting name as smallcase, as in Windows-MySQL combination an error occurs when we pass actual DBColumnName
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Integer bookId;
	@Column(name = "booktitle")
	private final String bookTitle;
	@Column(name = "authorname")
	private final String authorName;
	@Column(name = "currentstock")
	private final Integer stock;
	@Column(name = "isbn")
	private final String isbn;
	@Column(name = "genre")
	private final String genre;
	@Column(name = "publishedyear")
	private final String publishedYear;

	public Book()
	{
		bookId = -1;
		bookTitle = null;
		authorName = null;
		stock = -1;
		isbn = null;
		genre = null;
		publishedYear = null;
	}

	private Book(Builder builder)
	{
		this.bookId = builder.bookId;
		this.bookTitle = builder.bookTitle;
		this.authorName = builder.authorName;
		this.stock = builder.stock;
		this.isbn = builder.isbn;
		this.genre = builder.genre;
		this.publishedYear = builder.publishedYear;
	}

	public int getBookId()
	{
		return bookId;
	}

	public String getBookTitle()
	{
		return bookTitle;
	}

	public String getAuthorName()
	{
		return authorName;
	}

	public int getStock()
	{
		return stock;
	}

	public String getIsbn()
	{
		return isbn;
	}

	public String getGenre()
	{
		return genre;
	}

	public String getPublishedYear()
	{
		return publishedYear;
	}

	@JsonPOJOBuilder(withPrefix = "set")
	public static class Builder
	{
		Integer bookId = -1;
		String bookTitle;
		String authorName;
		int stock;
		String isbn;
		String genre;
		String publishedYear;

		public Builder setBookId(Integer bookId)
		{
			this.bookId = bookId;
			return this;
		}

		public Builder setBookTitle(String bookTitle)
		{
			this.bookTitle = bookTitle;
			return this;
		}

		public Builder setAuthorName(String authorName)
		{
			this.authorName = authorName;
			return this;
		}

		public Builder setStock(Integer stock)
		{
			this.stock = stock;
			return this;
		}

		public Builder setISBN(String isbn)
		{
			this.isbn = isbn;
			return this;
		}

		public Builder setGenre(String genre)
		{
			this.genre = genre;
			return this;
		}

		public Builder setPublishedYear(String publishedYear)
		{
			this.publishedYear = publishedYear;
			return this;
		}

		public Book build()
		{
			return new Book(this);
		}
	}
}
