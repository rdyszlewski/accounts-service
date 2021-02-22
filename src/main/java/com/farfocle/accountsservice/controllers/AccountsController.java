package com.farfocle.accountsservice.controllers;

import com.farfocle.accountsservice.authorization.JwtUtils;
import com.farfocle.accountsservice.authorization.UserDetailsImpl;
import com.farfocle.accountsservice.dto.LoginRequest;
import com.farfocle.accountsservice.dto.LoginResponse;
import com.farfocle.accountsservice.dto.SignUpData;
import com.farfocle.accountsservice.repository.AccountsRepository;
import com.farfocle.accountsservice.repository.User;
import com.farfocle.accountsservice.validation.SignUpValidationResult;
import com.farfocle.accountsservice.validation.SignUpValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = "application/json")
public class AccountsController {

    private final AccountsRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SignUpValidationService validationService;

    @Autowired
    public AccountsController(AccountsRepository userRepository, BCryptPasswordEncoder encoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils, SignUpValidationService service) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.validationService = service;
    }

    @PostMapping(path = "/sign-up")
    public SignUpValidationResult registerUser(@RequestBody SignUpData signupData) {
        SignUpValidationResult validationResult = validationService.validate(signupData);
        if (validationResult.isSuccess()) {
            User user = new User(signupData.getUsername(), signupData.getEmail(), passwordEncoder.encode(signupData.getPassword()));
            userRepository.save(user);
        }
        return validationResult;
//        if(userRepository.existsByUsername(signupData.getUsername())){
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken"));
//        }
//        if(userRepository.existsByEmail(signupData.getEmail())){
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already use"));
//        }
//        User user = new User(signupData.getUsername(), signupData.getEmail(), passwordEncoder.encode(signupData.getPassword()));
//        userRepository.save(user);
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    @PostMapping(path = "sign-in")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}
