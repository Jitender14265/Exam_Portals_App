package com.Exam.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.Exam.Service.Implementaion.UserDetailsServiceImpl;
import com.Exam.configuration.JwtUtils;
import com.Exam.entity.JwtRequest;
import com.Exam.entity.JwtResponse;
import com.Exam.entity.User;
import com.Exam.helper.UserNotFoundException;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;


    //generate token

//    @PostMapping("/generate-token")
//    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//
//        try {
//
//            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
//
//
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//            throw new Exception("User not found ");
//        }
//
//        /////////////authenticate
//
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
//        String token = this.jwtUtils.generateToken(userDetails);
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
//    
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println("Attempting to authenticate user: " + jwtRequest.getUsername());
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw e;
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        System.out.println("User authenticated, generating token for user: " + jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        System.out.println("Token generated: " + token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("User authenticated successfully");
        } catch (DisabledException e) {
            System.out.println("User disabled: " + e.getMessage());
            throw new Exception("USER DISABLED", e);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid credentials: " + e.getMessage());
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }



//    private void authenticate(String username, String password) throws Exception {
//
//        try {
//
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//
//        } catch (DisabledException e) {
//            throw new Exception("USER DISABLED " + e.getMessage());
//        } catch (BadCredentialsException e) {
//            throw new Exception("Invalid Credentials " + e.getMessage());
//        }
//    }

    //return the details of current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));

    }



}
