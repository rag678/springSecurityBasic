package com.learn.springbootsecuritylear;

import com.learn.springbootsecuritylear.models.User;
import com.learn.springbootsecuritylear.repo.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootSecurityLearApplication implements CommandLineRunner {
	@Autowired
	private Userrepository userrepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityLearApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setEmail("john@gmail.com");
		user.setPassword(this.bCryptPasswordEncoder.encode("deepak"));
		user.setUsername("john");
		user.setRole("ROLE_NORMAL");
		this.userrepository.save(user);
		User user1 = new User();
		user1.setEmail("abc@gmail.com");
		user1.setPassword(this.bCryptPasswordEncoder.encode("adminA"));
		user1.setUsername("Anurag");
		user1.setRole("ROLE_ADMIN");
		this.userrepository.save(user1);
	}
}
