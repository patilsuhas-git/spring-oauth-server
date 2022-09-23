package com.thisio.oauthauthorizationserver.service;

import com.thisio.oauthauthorizationserver.domain.Role;
import com.thisio.oauthauthorizationserver.domain.User;

import java.util.List;

public interface UserService {
  User saveUser(User user);
  Role saveRole(Role role);
  void addRoleToUser(String username, String roleName);
  User getUser(String username);
  List<User> getUsers();
}
