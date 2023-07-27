package ua.com.boot.libraryboot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.boot.libraryboot.models.Person;

/**
 * @author Anton Muzhytskyi
 */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{
	Optional<Person> findByName(String name);
	List<Person> findByNameStartingWith(String stringWith);
}
