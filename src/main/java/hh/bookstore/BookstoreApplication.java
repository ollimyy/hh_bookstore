package hh.bookstore;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}


	// add some testdata to the h2 test database on startup
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Book scrooge = new Book("The Life and Times of Scrooge McDuck", "Don Rosa", 2007, "978-0911903966", 82.41);
			Book hitchhiker = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1995, "9780345391803", 9.80);
			repository.save(scrooge);
			repository.save(hitchhiker);
		};
	}

}
