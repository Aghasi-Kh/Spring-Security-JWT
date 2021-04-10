package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserServiceImpl(
      UserRepository userRepository,
      RoleRepository roleRepository,
      BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User register(User user) {
    Role roleUser = roleRepository.findByName("ROLE_USER");
    List<Role> userRoles = new ArrayList<>();
    userRoles.add(roleUser);

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(userRoles);
    User registeredUser = userRepository.save(user);

    log.info("IN register - user: {} successfully registered", registeredUser);

    return registeredUser;
  }

  @Override
  public List<User> getAll() {
    List<User> result = userRepository.findAll();
    log.info("IN getAll - {} users found", result.size());
    return result;
  }

  @Override
  public User findByUsername(String username) {
    User result = userRepository.findByUsername(username);
    log.info("IN findByUsername - user: {} found by username: {}", result, username);
    return result;
  }

  @Override
  public User findById(Long id) {
    User result = userRepository.findById(id).orElse(null);

    if (result == null) {
      log.warn("IN findById - no user found by id: {}", id);
      return null;
    }

    log.info("IN findById - user: {} found by id: {}", result);
    return result;
  }

  @Override
  public void delete(Long id) {
    userRepository.deleteById(id);
    log.info("IN delete - user with id: {} successfully deleted");
  }
}
