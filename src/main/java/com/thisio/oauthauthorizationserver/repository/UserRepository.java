package com.thisio.oauthauthorizationserver.repository;

import com.thisio.oauthauthorizationserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
