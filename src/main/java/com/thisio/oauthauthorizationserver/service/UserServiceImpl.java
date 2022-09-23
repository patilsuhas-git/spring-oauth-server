package com.thisio.oauthauthorizationserver.service;

import com.thisio.oauthauthorizationserver.domain.Role;
import com.thisio.oauthauthorizationserver.domain.User;
import com.thisio.oauthauthorizationserver.repository.RoleRepository;
import com.thisio.oauthauthorizationserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
  private final UserRepository userRepo;
  private final RoleRepository roleRepo;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if(user == null) {
      log.error("User not found with username : {}", username);
      throw new UsernameNotFoundException("User not found");
    }
    log.info("User with username {} was found.", username);

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    });
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
  }

  @Override
  public User saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()) );
    return userRepo.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    return roleRepo.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    User user = userRepo.findByUsername(username);
    Role role = roleRepo.findByName(roleName);
    user.getRoles().add(role);
  }

  @Override
  public User getUser(String username) {
    return userRepo.findByUsername(username);
  }

  @Override
  public List<User> getUsers() {
    return userRepo.findAll();
  }
}
