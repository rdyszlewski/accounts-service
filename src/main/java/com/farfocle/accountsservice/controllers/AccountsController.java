package com.farfocle.accountsservice.controllers;

import com.farfocle.accountsservice.authorization.JwtUtils;
import com.farfocle.accountsservice.authorization.UserDetailsImpl;
import com.farfocle.accountsservice.dto.LoginRequest;
import com.farfocle.accountsservice.dto.LoginResponse;
import com.farfocle.accountsservice.dto.MessageResponse;
import com.farfocle.accountsservice.dto.SignUpData;
import com.farfocle.accountsservice.repository.User;
import com.farfocle.accountsservice.repository.AccountsRepository;
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

    private AccountsRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Autowired
    public AccountsController(AccountsRepository userRepository, BCryptPasswordEncoder encoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils){
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody SignUpData signupData){
        if(userRepository.existsByUsername(signupData.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken"));
        }
        if(userRepository.existsByEmail(signupData.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already use"));
        }
        User user = new User(signupData.getUsername(), signupData.getEmail(), passwordEncoder.encode(signupData.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping(path = "sign-in")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
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
