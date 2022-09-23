package com.thisio.oauthauthorizationserver;

import com.thisio.oauthauthorizationserver.domain.Role;
import com.thisio.oauthauthorizationserver.domain.User;
import com.thisio.oauthauthorizationserver.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class OauthauthorizationserverApplication {

  public static void main(String[] args) {
    SpringApplication.run(OauthauthorizationserverApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner run(UserService userService) {
    return args -> {
      userService.saveRole(new Role(null, "ROLE_USER"));
      userService.saveRole(new Role(null, "ROLE_MANAGER"));
      userService.saveRole(new Role(null, "ROLE_ADMIN"));
      userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

      userService.saveUser(new User(null, "Suhas Patil", "suhas", "1234", new ArrayList<>()));
      userService.saveUser(new User(null, "Shraddha Ghogare", "shraddha", "5678", new ArrayList<>()));
      userService.saveUser(new User(null, "Arun Balchandran", "arun", "9012", new ArrayList<>()));

      userService.addRoleToUser("suhas", "ROLE_SUPER_ADMIN");
      userService.addRoleToUser("shraddha", "ROLE_MANAGER");
      userService.addRoleToUser("shraddha", "ROLE_SUPER_ADMIN");
      userService.addRoleToUser("arun", "ROLE_USER");
      userService.addRoleToUser("arun", "ROLE_ADMIN");
      userService.addRoleToUser("arun", "ROLE_ADMIN");
    };
  }
}
