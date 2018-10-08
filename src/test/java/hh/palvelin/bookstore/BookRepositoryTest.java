package hh.palvelin.bookstore;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelin.bookstore.model.Book;
import hh.palvelin.bookstore.model.BookRepository;
import hh.palvelin.bookstore.model.Category;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookrepo;

	@Test
	public void repoHasContent() {
		Iterable<Book> books = bookrepo.findAll();
		assertThat(books).isNotEmpty();
		assertThat(books).size().isGreaterThan(1);
	}
	
	@Test
	public void bookCreate() {
		Book book = new Book("Testing with Junit", "Writer", new Category("Tests"));
		assertThat(book).isNotNull();
		assertThat(book.getTitle()).isNotEmpty();
	}
	
	@Test
	public void bookBlankNames() {
		Book book = new Book("", "", new Category("Tests"));
		assertThat(book.getTitle()).isBlank();
		assertThat(book.getAuthor()).isBlank();
	}
	@Test
	public void bookAllParameters() {
		Book book = new Book("Testing with Junit", "Writer", 1999, "123456", 12.59, new Category("Tests"));
		assertThat(book).hasNoNullFieldsOrProperties();
		assertThat(book.getPrice()).isEqualTo(12.59);
		assertThat(book.getPrice()).isNotEqualTo(13);
	}

}
