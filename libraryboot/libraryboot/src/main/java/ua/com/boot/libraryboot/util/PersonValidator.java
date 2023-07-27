package ua.com.boot.libraryboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.com.boot.libraryboot.services.PeopleService;
import ua.com.boot.libraryboot.models.Person;

/**
 * @author Anton Muzhytskyi
 */

@Component
public class PersonValidator implements Validator {
	
	private final PeopleService peopleService;

	@Autowired
	public PersonValidator(PeopleService peopleService) {
		this.peopleService = peopleService;
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Person.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Person person = (Person) o;
		if(peopleService.getPersonByName(person.getName()).isPresent()) {
			errors.rejectValue("name", "",  "Customer with this name exist already");
		}
	}
}
