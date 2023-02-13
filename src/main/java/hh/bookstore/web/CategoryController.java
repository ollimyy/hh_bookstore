package hh.bookstore.web;

import hh.bookstore.domain.Category;
import hh.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("/categorylist")
    public String listAllCategories(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        return "categorylist";
    }

    @GetMapping("/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @PostMapping("/savecategory")
    public String saveCategory(Category category) {
        categoryRepo.save(category);
        return "redirect:categorylist";
    }
}