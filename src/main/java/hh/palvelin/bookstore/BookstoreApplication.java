package hh.palvelin.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelin.bookstore.model.Book;
import hh.palvelin.bookstore.model.BookRepository;
import hh.palvelin.bookstore.model.Category;
import hh.palvelin.bookstore.model.CategoryRepository;
import hh.palvelin.bookstore.model.User;
import hh.palvelin.bookstore.model.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookdemo(BookRepository repo, CategoryRepository catrepo, UserRepository urepo) {
		return (args) -> {
			catrepo.save(new Category("Kauhu"));
			catrepo.save(new Category("Trilleri"));
			catrepo.save(new Category("Komedia"));
						
			repo.save(new Book("Testikirja", "Kari Kirjailija", catrepo.findByName("Kauhu").get(0)));
			repo.save(new Book("Kirja Kirja", "Kira Kirja", catrepo.findByName("Trilleri").get(0)));
			
			//user/user
			User user1 = new User("user", "user@user.test", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			//admin/admin
			User user2 = new User("admin", "admin@admin.test", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			//johndoe/john
			User john = new User("johndoe", "john@doe.test", "$2a$04$f9jdEzwmqW4uVKwzl25CaONZImBpKXt6aNy8dGMPpQyWY8WJPdxuy", "USER");
			//marysue/mary
			User mary = new User("marysue", "mary@sue.test", "$2a$04$YDo0IfHLUoX7gwV4CSgDcOya9OmeTZdMWaih2TLFpfMhQr9D/fOyS", "ADMIN");
			urepo.save(user1);
			urepo.save(user2);
			urepo.save(john);
			urepo.save(mary);
			
			/*for (Book book : repo.findAll()) {
				System.out.println(book.toString());
			}*/
		};
	}
}
