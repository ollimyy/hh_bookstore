package hh.bookstore.domain;

import jakarta.persistence.*;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookId;
	private String title;
	private String author;
	private int releaseYear;
	private String isbn;
	private double price;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;


	public Book(String title, String author, int releaseYear, String isbn, double price, Category category) {
		this.title = title;
		this.author = author;
		this.releaseYear = releaseYear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}

	public Book() {

	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long id) {
		this.bookId = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int year) {
		this.releaseYear = year;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book{" +
				"bookId=" + bookId +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", releaseYear=" + releaseYear +
				", isbn='" + isbn + '\'' +
				", price=" + price +
				", category=" + category +
				'}';
	}
}