package ua.com.boot.libraryboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.boot.libraryboot.models.Book;
import ua.com.boot.libraryboot.models.Person;

/**
 * @author Anton Muzhytskyi
 */

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
	
	List<Book> findByBookName(String bookName);
	List<Book> findByAuthor(String author);
	List<Book> findByOwner(Person owner);
	List<Book> findByBookNameStartingWith(String bookName);
}
