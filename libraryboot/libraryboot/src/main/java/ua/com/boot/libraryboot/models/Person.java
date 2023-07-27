package ua.com.boot.libraryboot.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Anton Muzhytskyi
 */

@Entity
@Table(name="Person")
public class Person {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Name shouldnot be empty")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	@Column(name="name")
	private String name;
	
	@Min(value = 1920, message = "Year of birth should be greater than 1920")
	@Column(name="year_of_birth")
	private int yearOfBirth;
	
	@OneToMany(mappedBy = "owner")
	private List<Book> books;
	
	
	public Person () {}
	
	public Person(
			@NotEmpty(message = "Name shouldnot be empty") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String name,
			@Min(value = 1920, message = "Year of birth should be greater than 1920") int yearOfBirth) {
		super();
		this.name = name;
		this.yearOfBirth = yearOfBirth;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public int getYearOfBirth() {return yearOfBirth;}
	public void setYearOfBirth(int yearOfBirth) {this.yearOfBirth = yearOfBirth;}
	
	public List<Book> getBooks() {return books;}
	public void setBooks(List<Book> items) {this.books = items;}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", yearOfBirth=" + yearOfBirth + "]";
	}
}
