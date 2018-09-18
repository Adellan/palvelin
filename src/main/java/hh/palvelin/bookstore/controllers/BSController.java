package hh.palvelin.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.palvelin.bookstore.model.Book;
import hh.palvelin.bookstore.model.BookRepository;
import hh.palvelin.bookstore.model.CategoryRepository;

@Controller
public class BSController {
	@Autowired
	private BookRepository repo;
	@Autowired
	private CategoryRepository catrepo;

	@GetMapping("index")
	public String etusivu() {
		return "index";
	}
	
	@GetMapping("booklist")
	public String lista(Model model) {
		model.addAttribute("books", repo.findAll());
		return "booklist";
	}
	
	@GetMapping("add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categorys", catrepo.findAll());
        return "addbook";
    }
	
	@PostMapping("save")
    public String save(Book book){
        /*
		if(book.getId() != null) {
        	book.setId(book.getId());
        	repo.save(book);
        } else {
        	repo.save(book);
        }
        */
		repo.save(book);
        return "redirect:booklist";
    }
	
	@GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
    	repo.deleteById(id);
        return "redirect:../booklist";
    }
	
	@GetMapping("edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		Book book = repo.findById(id).get();
		model.addAttribute("book", book);
		model.addAttribute("categorys", catrepo.findAll());
		return "editbook";
		
	}

}
