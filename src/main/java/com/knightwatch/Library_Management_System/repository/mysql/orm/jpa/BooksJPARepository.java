package com.knightwatch.Library_Management_System.repository.mysql.orm.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knightwatch.Library_Management_System.model.Book;

@Repository("Repository-books-mysql-orm-jpa")
public interface BooksJPARepository extends JpaRepository<Book, Integer>
{

}
