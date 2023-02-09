package hh.bookstore.web;

import hh.bookstore.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {


	@Autowired //inject repository instance
	private BookRepository repository;
	@GetMapping("/index")
	public String sayGreetings() {
		return "index";
	}

	@GetMapping("/booklist")
	public String listAllBooks(Model model) {
		// get all books from database and add them as an attribute to the template
		model.addAttribute("books", repository.findAll());
		return "booklist"; //booklist.html
	}

	@GetMapping("delete/{id}")
	public String deleteStudent(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
}