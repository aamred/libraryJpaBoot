package ua.com.boot.libraryboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ua.com.boot.libraryboot.models.Person;
//import ua.com.boot.libraryboot.services.BooksService;
import ua.com.boot.libraryboot.services.PeopleService;
import ua.com.boot.libraryboot.util.PersonValidator;

/**
 * @author Anton Muzhytskyi
 */

@Controller
@RequestMapping("/people")
public class PeopleController {
	
	private PeopleService peopleService;
	//private BooksService booksService;
	private PersonValidator personValidator;
	
	@Autowired
	public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
		this.peopleService = peopleService;
		this.personValidator = personValidator;
	}    

	@GetMapping() 
	public String index (Model model) {
		model.addAttribute("people", peopleService.findAll());
		return "people/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", peopleService.findOne(id));
		model.addAttribute("books", peopleService.getBookByPersonId(id));
		return "people/show";
	}
	
	@GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }
		
	@PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
		if (bindingResult.hasErrors())
            return "people/new";

       peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
       model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

       peopleService.update(id, person);
        return "redirect:/people";
    }
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		peopleService.delete(id);
		return "redirect:/people";
	}
}
