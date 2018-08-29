package com.service.impl;

import com.dto.UserLoginDTO;
import com.exception.CustomException;
import com.model.User;
import com.repository.UserRepository;
import com.security.JwtTokenProvider;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

//    @Override
//    public String signup(User user) {
//        if ((!userRepository.existsByEmail(user.getEmail()))||(!userRepository.existsByUsername(user.getUsername()))) {
////        if ((!userRepository.existsByUsername(user.getUsername())) || (!userRepository.existsByEmail(user.getEmail()))) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
//            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
//        } else {
//            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }

    @Override
    public String signup(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CustomException("Email '" + user.getEmail() + "' is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new CustomException("Username '" + user.getUsername() + "' is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        }
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @Override
    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmailOrPhone(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByEmailOrPhone(userLoginDTO.getEmail(), userLoginDTO.getPhone());
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @Override
    public User findByResetToken(String resetToken) {
        User user = userRepository.findByResetToken(resetToken);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

}
