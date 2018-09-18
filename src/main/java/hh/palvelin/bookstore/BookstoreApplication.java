package hh.palvelin.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelin.bookstore.model.Book;
import hh.palvelin.bookstore.model.BookRepository;
import hh.palvelin.bookstore.model.Category;
import hh.palvelin.bookstore.model.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookdemo(BookRepository repo, CategoryRepository catrepo) {
		return (args) -> {
			catrepo.save(new Category("Kauhu"));
			catrepo.save(new Category("Trilleri"));
			catrepo.save(new Category("Komedia"));
						
			repo.save(new Book("Testikirja", "Kari Kirjailija", catrepo.findByName("Kauhu").get(0)));
			repo.save(new Book("Kirja Kirja", "Kira Kirja", catrepo.findByName("Trilleri").get(0)));
			
			/*for (Book book : repo.findAll()) {
				System.out.println(book.toString());
			}*/
		};
	}
}
