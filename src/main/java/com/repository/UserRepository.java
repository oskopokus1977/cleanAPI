package com.repository;

import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);
  boolean existsByEmail(String email);

  User findByUsername(String username);

  User findByEmailOrPhone(String email, String phone);

  @Override
  List<User> findAll();

  @Transactional
  void deleteByUsername(String username);

  User findByEmail(String email);

  User findByResetToken(String resetToken);

}
