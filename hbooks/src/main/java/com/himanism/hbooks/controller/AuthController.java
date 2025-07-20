//package com.himanism.hbooks.controller;
//
//import com.himanism.hbooks.dto.*;
//import com.himanism.hbooks.entity.User;
//import com.himanism.hbooks.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//@CrossOrigin(origins =  "http://localhost:3000")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signupRequest){
//        try {
//            User user = userService.createUser(signupRequest);
//            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
//        } catch (RuntimeException e){
//            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
//        }
//    }
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            // For simplicity, no JWT token — just success message
//            return ResponseEntity.ok(new ApiResponse(true, "User signed in successfully"));
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "Invalid username or password"));
//        }
//    }
//}
