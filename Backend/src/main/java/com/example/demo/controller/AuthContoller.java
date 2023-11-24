package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.security.JwtHelper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthContoller {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;



//    http://localhost:8080/auth/login
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
        try {
            this.doAuthenticate(request.getUsername(), request.getPassword());
        }
        catch(BadCredentialsException e){
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);


        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try{
            manager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username or Password!! ");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(){
        return "Credentials Invalid!!";
    }


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public ResponseEntity<String> greet(){
        return ResponseEntity.ok(" successfully.");
    }

//    http://localhost:8080/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        System.out.println(user);
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()){
            return ResponseEntity.badRequest().body("Username , password and email is required.");
        }
        if(userService.findByUserName(user.getUsername()) !=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }



}
