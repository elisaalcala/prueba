package com.app.alcala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.alcala.entities.User;
import com.app.alcala.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseUsersLoader {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	private void initDatabase() {
	    userRepository.save(new User("johndoe", passwordEncoder.encode("pass"), "USER"));
	    userRepository.save(new User("janedoe", passwordEncoder.encode("adminpass"), "ADMIN"));
	    userRepository.save(new User("pepe", passwordEncoder.encode("pass"), "USER"));
	    userRepository.save(new User("maria", passwordEncoder.encode("pass"), "USER"));
	    userRepository.save(new User("antonio", passwordEncoder.encode("pass"), "USER"));
	    userRepository.save(new User("laura", passwordEncoder.encode("pass"), "USER"));
	    userRepository.save(new User("carlos", passwordEncoder.encode("pass"), "USER"));
	    userRepository.save(new User("ana", passwordEncoder.encode("pass"), "USER"));
	    userRepository.save(new User("juan", passwordEncoder.encode("pass"), "USER"));
	    userRepository.save(new User("isabel", passwordEncoder.encode("pass"), "USER"));
	    userRepository.save(new User("david", passwordEncoder.encode("pass"), "USER"));

	}

}
