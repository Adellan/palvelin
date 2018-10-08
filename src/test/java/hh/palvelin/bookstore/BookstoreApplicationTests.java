package hh.palvelin.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelin.bookstore.controllers.BSController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {
	
	@Autowired
	private BSController cont;

	@Test
	public void contextLoads() {
		assertThat(cont).isNotNull();
	}
	
	@Test
	public void contHasRepo() {
		assertThat(cont).hasFieldOrProperty("repo");
	}
	
}
