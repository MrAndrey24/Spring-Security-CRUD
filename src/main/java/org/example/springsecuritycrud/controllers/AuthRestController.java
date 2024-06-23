package org.example.springsecuritycrud.controllers;

import org.example.springsecuritycrud.dto.LoginResponse;
import org.example.springsecuritycrud.entities.Role;
import org.example.springsecuritycrud.entities.User;
import org.example.springsecuritycrud.entities.enums.RoleEnum;
import org.example.springsecuritycrud.repository.RoleRepository;
import org.example.springsecuritycrud.repository.UserRepository;
import org.example.springsecuritycrud.service.AuthenticationService;
import org.example.springsecuritycrud.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthRestController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;



    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthRestController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody User user) {
        User authenticatedUser = authenticationService.authenticate(user);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        Optional<User> foundedUser = userRepository.findByEmail(user.getEmail());

        foundedUser.ifPresent(loginResponse::setAuthUser);

        return ResponseEntity.ok(loginResponse);
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
//
//        if (optionalRole.isEmpty()) {
//            return null;
//        }
//        user.setRole(optionalRole.get());
//        User savedUser = userRepository.save(user);
//        return ResponseEntity.ok(savedUser);
//    }

}