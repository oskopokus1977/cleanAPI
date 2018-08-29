package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import com.dto.UserLoginDTO;
import com.exception.CustomException;
import com.repository.UserRepository;
import com.service.UserService;
import com.util.MailSender;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.dto.UserDataDTO;
import com.dto.UserResponseDTO;
import com.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")//crossBrowser enable
@RestController
@RequestMapping("/users")
@Api(tags = "Controller")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public UserResponseDTO login(@ApiParam(name = "Signin User", value = "Use {email+password}, or {phone+password}")
                                 @RequestBody UserLoginDTO userLoginDTO) {
        User user = userService.findByEmailOrPhone(userLoginDTO);
        String token = userService.signin(user.getUsername(), userLoginDTO.getPassword());
        UserResponseDTO userResponseDTO = modelMapper.map(
                userService.search(user.getUsername()), UserResponseDTO.class);
        userResponseDTO.setToken(token);
        return userResponseDTO;
    }


//    @PostMapping("/signup")
//    @ApiOperation(value = "${UserController.signup}")
//    @ApiResponses(value = {
//            @ApiResponse(code = 400, message = "Something went wrong"),
//            @ApiResponse(code = 403, message = "Access denied"),
//            @ApiResponse(code = 422, message = "Username is already in use"),
//            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
//    public UserResponseDTO signup(@ApiParam("Signup User") @RequestBody UserDataDTO userDTO) {
//        String token = userService.signup(modelMapper.map(userDTO, User.class));
//        UserResponseDTO userResponseDTO = modelMapper.map(
//                userService.search(userDTO.getUsername()), UserResponseDTO.class);
//        userResponseDTO.setToken(token);
//        return userResponseDTO;
//    }

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422, message = "Username is already in use"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<Object> signup(@ApiParam("Signup User")@Valid @RequestBody UserDataDTO userDTO) {
        String token = userService.signup(modelMapper.map(userDTO, User.class));
        UserResponseDTO userResponseDTO = modelMapper.map(
                userService.search(userDTO.getUsername()), UserResponseDTO.class);
        userResponseDTO.setToken(token);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{username}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${UserController.delete}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "The user doesn't exist"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "The user doesn't exist"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDTO.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }

    @GetMapping(value = "/all")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${UserController.all}", response = UserResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<UserResponseDTO> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (User user : userList) {
            userResponseDTOList.add(modelMapper.map(user, UserResponseDTO.class));
        }
        return userResponseDTOList;
    }


    @PostMapping("/forgot")
    @ApiOperation(value = "${UserController.forgotPassword}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "The user doesn't exist")})
    public void forgotPassword(@ApiParam(name = "Forgot password", value = "Use {email}, or {phone}")
                               @RequestBody UserLoginDTO userLoginDTO,
                               HttpServletRequest request) {
        User user = userService.findByEmailOrPhone(userLoginDTO);

        user.setResetToken(UUID.randomUUID().toString());
        userRepository.save(user);

//   it is for local usage "http://localhost:8080/users/forgot/........"
//        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort();
//        String resetMassage = "Для смены пароля перейдите по следующей ссылке:\n" +
//                appUrl + "/users/forgot/check?resetToken=" + user.getResetToken();

//   it is for global usage "http://goclean.in.ua/api/users/forgot/........"
        String appUrl = request.getScheme() + "://" + request.getServerName();
        String resetMassage = "Для смены пароля перейдите по следующей ссылке:\n" +
                appUrl + "/api/users/forgot/check?resetToken=" + user.getResetToken();

        MailSender.send("Восстановление пароля сервиса Goclean",
                resetMassage, new String[]{user.getEmail()});
    }

    @GetMapping(value = "/forgot/check")
    @ApiOperation(value = "${UserController.checkResetToken}")
//    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "The resetToken doesn't exist")})
    public void checkResetToken(@ApiParam("ResetToken") @RequestParam String resetToken) {
        userService.findByResetToken(resetToken);
    }

    @PostMapping("/forgot/refresh")
    @ApiOperation(value = "${UserController.refreshPassword}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "The resetToken doesn't exist")})
    public void refreshPassword(
            @ApiParam("ResetToken") @RequestParam String resetToken,
            @ApiParam("NewPassword") @RequestParam String newPassword) {
        User user = userService.findByResetToken(resetToken);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        userRepository.save(user);
    }
}
