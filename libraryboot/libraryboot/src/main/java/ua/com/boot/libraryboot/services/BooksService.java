package ua.com.boot.libraryboot.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.boot.libraryboot.models.Book;
import ua.com.boot.libraryboot.models.Person;
import ua.com.boot.libraryboot.repositories.BooksRepository;

/**
 * @author Anton Muzhytskyi
 */

@Service
@Transactional(readOnly=true)
public class BooksService {

	private final BooksRepository booksRepository;

	@Autowired
	public BooksService(BooksRepository booksRepository) {
		this.booksRepository = booksRepository;
	}
	
	public List<Book> findAll(boolean sortByYear){
		if(sortByYear)
			return booksRepository.findAll(Sort.by("year_of_print"));
		else
			return booksRepository.findAll();
	}
	
	public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear){
		if(sortByYear)
			return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year_of_ptint"))).getContent();
		else
			return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
	}
	
	public Book findOne(int id){
		Optional<Book> foundBook = booksRepository.findById(id);
		return foundBook.orElse(null);
	}
		
	public List<Book> findByBookName(String query){
		return booksRepository.findByBookNameStartingWith(query);
	}
	
	@Transactional
	public void save(Book book ) {
		booksRepository.save(book);
	}
	
	@Transactional
	public void update(int id, Book updatedBook) {
		Book bookToBeUpdated = booksRepository.findById(id).get();
		updatedBook.setId(id);
		updatedBook.setOwner(bookToBeUpdated.getOwner());
		booksRepository.save(updatedBook);
	}
	
	@Transactional
	public void delete(int id) {
		booksRepository.deleteById(id);	
	}
	
	public Person getBookOwner(int id) {
		return booksRepository.findById(id).map(Book::getOwner).orElse(null);
	}
	
	@Transactional
	public void release(int id) {
		booksRepository.findById(id).ifPresent(
				book -> {
					book.setOwner(null);
					book.setTakenAt(null);
				});
	}
	
	@Transactional
	public void assign(int id, Person selectedPerson) {
		booksRepository.findById(id).ifPresent(
				book -> {
					book.setOwner(selectedPerson);
					book.setTakenAt(new Date());
				}
		);
	}	
}
