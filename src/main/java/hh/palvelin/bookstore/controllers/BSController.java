package hh.palvelin.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BSController {

	@GetMapping("index")
	public String etusivu() {
		return "index";
	}
}
