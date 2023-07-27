package ua.com.boot.libraryboot.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.boot.libraryboot.models.Book;
import ua.com.boot.libraryboot.models.Person;
import ua.com.boot.libraryboot.repositories.PeopleRepository;

/**
 * @author Anton Muzhytskyi
 */

@Service
@Transactional(readOnly = true)
public class PeopleService {
	
	private final PeopleRepository peopleRepository;

	public PeopleService(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}
	
	public List<Person> findAll(){
		return peopleRepository.findAll();
	}
	
	public Person findOne(int id) {
		Optional<Person>  foundPerson = peopleRepository.findById(id);
		return foundPerson.orElse(null);
	}
	
	@Transactional
	public void save(Person person) {
		peopleRepository.save(person);
	}
	
	@Transactional
	public void update(int id, Person updatedPerson) {
		updatedPerson.setId(id);
		peopleRepository.save(updatedPerson);
	}
	
	@Transactional
	public void delete(int id) {
		peopleRepository.deleteById(id);
	}

	public Optional<Person> getPersonByName(String name) {
		return peopleRepository.findByName(name);
	}
	
	public List<Book> getBookByPersonId(int id){
		Optional<Person> person = peopleRepository.findById(id);
		if(person.isPresent()) {
			Hibernate.initialize(person.get().getBooks());
			person.get().getBooks().forEach(book ->{
				long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
				if(diffInMillies > 864000000) 
					book.setExpired(true);
			});
			return person.get().getBooks();
		} else 
			return Collections.emptyList();
	}	
}
