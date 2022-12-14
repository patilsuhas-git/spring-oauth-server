package com.thisio.oauthauthorizationserver.repository;

import com.thisio.oauthauthorizationserver.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
