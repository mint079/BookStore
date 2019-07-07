    
package fi.haagahelia.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import for logging activities

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.domain.CateRepository;
import fi.haagahelia.Bookstore.domain.Category;
import fi.haagahelia.Bookstore.domain.User;
import fi.haagahelia.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository bookRepo, CateRepository cateRepo, UserRepository userRepo) {
		return (args) -> {
			
			log.info(">>test data injection");
			
			log.info("Category data");
			Category cate1 = new Category("Novel");
			Category cate2 = new Category("Poem");
			
			cateRepo.save(cate1);
			cateRepo.save(cate2);
			
			log.info("Book data");
			Book book1 = new Book("Learn how to read", "A", 2019, "a1", (float) 30.02, cate1);
			Book book2 = new Book("How to fly", "B", 2019, "a2", (float) 30.02, cate2);
			Book book3 = new Book("How to swim", "C", 2019, "a3", (float) 30.01, cate1);
			
			bookRepo.save(book1);
			bookRepo.save(book2);
			bookRepo.save(book3);
			
			log.info("User data");
			// test user role:USER id:user pw:password
			User user1 = new User("user", "$2a$10$NlvPgFjQ0gaWaUrvnYothOeFgRkmS22SiYfNac/M1eucq2LbD8o5G", "", "USER");
			// test user role:ADMIN id:admin pw:admin
			User admin1 = new User("admin", "$2a$10$gosnLht4KLxiTlg4NFY5reGutRy4OJ0Sli4rzcVkkJQrfU/R7qnH6", "admin@bookstore.fi", "ADMIN");

			userRepo.save(user1);
			userRepo.save(admin1);
			
			log.info("Current users in db:");
			for (User user : userRepo.findAll()) {
				log.info(user.toString());
			}
		};
	}
	
}
	

