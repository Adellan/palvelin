package hh.palvelin.bookstore.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelin.bookstore.model.Book;
import hh.palvelin.bookstore.model.BookRepository;
import hh.palvelin.bookstore.model.CategoryRepository;

@Controller
public class BSController {
	@Autowired
	private BookRepository repo;
	@Autowired
	private CategoryRepository catrepo;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("index")
	public String etusivu() {
		return "index";
	}
	
	@GetMapping("booklist")
	public String lista(Model model) {
		model.addAttribute("books", repo.findAll());
		return "booklist";
	}
	
	//rest booklist
	@GetMapping("books")
	public @ResponseBody List<Book> list(){
		return (List<Book>) repo.findAll();
	}
	
	//rest find one book
	@GetMapping("book/{id}")
    public @ResponseBody Optional<Book> findBook(@PathVariable("id") Long id) {	
    	return repo.findById(id);
    } 
	
	@GetMapping("add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categorys", catrepo.findAll());
        return "addbook";
    }
	
	
	@PostMapping("saveAdd")
    public String saveAdd(@Valid Book book, BindingResult bindingResult){	
		if (bindingResult.hasErrors()) {
            return "addbook";
        }
		repo.save(book);
        return "redirect:booklist";

    }
	
	@PostMapping("saveEdit")
    public String saveEdit(@Valid Book book, BindingResult bindingResult){	
		if (bindingResult.hasErrors()) {			
            return "editbook";
        }
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
