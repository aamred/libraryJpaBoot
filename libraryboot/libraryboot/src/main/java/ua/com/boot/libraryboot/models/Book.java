package ua.com.boot.libraryboot.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Anton Muzhytskyi
 */

@Entity
@Table(name="Book")
public class Book {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
	@Column(name="book_name")
	private String bookName;
	
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
	@Column(name="author")
	private String author;
	
	@Min(value = 0, message = "Year of print should be greater than 0")
	@Column(name="year_of_print")
	private int yearOfPrint;
	
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName="id")
	private Person owner;
	
	@Column(name="taken_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date takenAt;
	
	@Transient
	private boolean expired;
	
	
	public Book() {}

	public Book(
			@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters") String bookName,
			@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters") String author,
			@Min(value = 0, message = "Year of print should be greater than 0") int yearOfPrint) {
		this.bookName = bookName;
		this.author = author;
		this.yearOfPrint = yearOfPrint;
	}

	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getBookName() {return bookName;}
	public void setBookName(String bookName) {this.bookName = bookName;}

	public String getAuthor() {return author;}
	public void setAuthor(String author) {this.author = author;}

	public int getYearOfPrint() {return yearOfPrint;}
	public void setYearOfPrint(int yearOfPrint) {this.yearOfPrint = yearOfPrint;}
	
	public Person getOwner() {return owner;}
	public void setOwner(Person owner) {this.owner = owner;}
	
	public Date getTakenAt() {return takenAt;}
	public void setTakenAt(Date takenAt) {this.takenAt = takenAt;}

	public boolean isExpired() {return expired;}
	public void setExpired(boolean expired) {this.expired = expired;}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author=" + author + ", yearOfPrint=" + yearOfPrint
				+ "]";
	}
}
