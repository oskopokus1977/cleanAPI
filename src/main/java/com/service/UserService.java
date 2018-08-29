package com.service;

import com.dto.UserLoginDTO;
import com.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    String signin(String username, String password);

    String signup(User user);

    void delete(String username);

    User search(String username);

    User whoami(HttpServletRequest req);

    List<User> getAllUsers();

    User findByEmailOrPhone(UserLoginDTO userLoginDTO);

    User findByEmail(String email);

    User findByResetToken(String resetToken);
}
