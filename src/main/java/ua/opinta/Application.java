package ua.opinta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.opinta.core.Role;
import ua.opinta.core.User;
import ua.opinta.dao.RoleRepository;
import ua.opinta.dao.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Run command line runner");

		// fill role table
		Role role0 = new Role(0l, "ROLE_ADMIN");
		Role role1 = new Role(0l, "ADMIN");
		Role role2 = new Role(0l, "DEFAULT");

		Role savedRole0 = roleRepository.save(role0);
		logger.info("Saved to db role: " + savedRole0);
		Role savedRole1 = roleRepository.save(role1);
		logger.info("Saved to db role: " + savedRole1);
		Role savedRole2 = roleRepository.save(role2);
		logger.info("Saved to db role: " + savedRole2);

		// fill user_detail table
		User user1 = new User(0l, "Oleh", "pass1");
		user1.addRole(savedRole1);
		user1.addRole(savedRole2);
		User user2 = new User(0l, "Daha", "pass2");
		user2.addRole(savedRole2);
		User user3 = new User(0l, "roy", "spring");
		user3.setIsActive(true);
		user3.addRole(savedRole0);
		user3.addRole(savedRole1);
		user3.addRole(savedRole2);

		User savedUser = userRepository.save(user1);
		logger.info("Saved to db user: " + savedUser);
		savedUser = userRepository.save(user2);
		logger.info("Saved to db user: " + savedUser);
		savedUser = userRepository.save(user3);
		logger.info("Saved to db user: " + savedUser);

	}
}
