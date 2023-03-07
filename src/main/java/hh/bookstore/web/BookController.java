package hh.bookstore.web;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {

	@Autowired //inject repository instance
	private BookRepository bookRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping("/index")
	public String sayGreetings() {
		return "index";
	}

	@GetMapping("/booklist")
	public String listAllBooks(Model model) {
		// get all books from database and add them as an attribute to the template
		model.addAttribute("books", bookRepo.findAll());
		return "booklist"; //booklist.html
	}

	//RESTful service to get all books
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepo.findAll();
	}

	@GetMapping("/book/{id}")
	public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {
		return bookRepo.findById(bookId).get(); //TODO: error message when book not found
	}

	//create an empty book object, add it to the model and redirect to a form for adding a book
	@GetMapping("/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepo.findAll()); //also add all categories to template
		return "addbook";
	}

	//update or add a book in the database
	@PostMapping("/savebook")
	public String save(Book book) {
		bookRepo.save(book); //if id in database -> SQL Update
		return "redirect:booklist";
	}

	// pass the book we want to edit to the edit book template
	@GetMapping("/editbook/{id}")
	public String editStudent(@PathVariable("id") Long bookId, Model model) {
		Book bookToEdit = bookRepo.findById(bookId).get(); //TODO: error message when book not found
		model.addAttribute("book", bookToEdit);
		return "editbook";
	}

	@GetMapping("deletebook/{id}")
	@PreAuthorize("hasAuthority('ADMIN')") // only admin can delete books
	public String deleteStudent(@PathVariable("id") Long bookId) {
		bookRepo.deleteById(bookId);
		return "redirect:../booklist";
	}
}