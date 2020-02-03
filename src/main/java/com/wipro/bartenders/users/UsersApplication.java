package com.wipro.bartenders.users;

import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateJPA(UserRepository userRepository, RoleRepository roleRepository){
		return (args) -> {
			User user1 = new User(1, "trillian", "Tricia", "McMillan", "1994-01-01", "tricia42@dolphins.com");
			User user2 = new User(2, "trillian2", "Tricia2", "McMillan2", "1994-01-01", "tricia42_2@dolphins.com");
			userRepository.save(user1);
			userRepository.save(user2);
			Role role1 = new Role("root", 0);
			Role role2 = new Role("read", 1);
			roleRepository.saveAll(Arrays.asList(role1, role2));
		};
	}

	@Bean
	public ModelMapper  modelMapper(){
		return new ModelMapper();
	}

}
