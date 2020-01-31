package com.wipro.bartenders.users;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateJPA(UserRepository repository){
		return (args) -> {
			User user1 = new User(1, "trillian", "Tricia", "McMillan", "1994-01-01", "tricia42@dolphins.com");
			User user2 = new User(2, "trillian2", "Tricia2", "McMillan2", "1994-01-01", "tricia42_2@dolphins.com");
			repository.save(user1);
			repository.save(user2);
		};
	}

	@Bean
	public ModelMapper  modelMapper(){
		return new ModelMapper();
	}

}
