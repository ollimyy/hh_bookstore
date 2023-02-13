package hh.bookstore;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.Category;
import hh.bookstore.domain.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}


	// add some testdata to the h2 test database on startup
	@Bean
	public CommandLineRunner demo(BookRepository bookRepo, CategoryRepository categoryRepo) {
		return (args) -> {
			// categories
			Category comics = new Category("Comics");
			Category scifi = new Category("Scifi");
			categoryRepo.save(comics);
			categoryRepo.save(scifi);

			//books
			Book scrooge = new Book("The Life and Times of Scrooge McDuck", "Don Rosa", 2007, "978-0911903966", 82.41, comics);
			Book hitchhiker = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1995, "978-0345391803", 9.80, scifi);
			bookRepo.save(scrooge);
			bookRepo.save(hitchhiker);



			log.info("fetch all categories");
			for (Category category : categoryRepo.findAll()) {
				log.info(category.toString());
			}
		};
	}
}
