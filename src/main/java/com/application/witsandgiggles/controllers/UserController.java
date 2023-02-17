package com.application.witsandgiggles.controllers;

import com.application.witsandgiggles.auth.AuthManager;
import com.application.witsandgiggles.auth.AuthenticationRequest;
import com.application.witsandgiggles.auth.AuthenticationService;
import com.application.witsandgiggles.auth.RegisterRequest;
import com.application.witsandgiggles.domain.User;
import com.application.witsandgiggles.models.UserMessage;
import com.application.witsandgiggles.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path ="/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final transient AuthManager authManager;

    /*
    @RequestMapping("/list")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        RegisterRequest request = new RegisterRequest();
        model.addAttribute("registerRequest", request);
        return "users/register_form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerRequest") RegisterRequest request) {
        try {
            authenticationService.register(request);
            return "redirect:/users/register?successful";
        } catch (Exception e) {
            return "redirect:/users/register?unsuccessful";
        }
    }


    // https://www.bezkoder.com/react-jwt-auth/

    //login form
    @RequestMapping("/login")
    public String login() {
        return "users/login_form";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "users/login_form";
    }

    @GetMapping("/{id}")
    public String getUserPage(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userService.getUser(id);
        if (user.isEmpty()) {
            return "users/list"; // if the user doesn't exist, go to user list
                                 //TODO maybe change that if called from more places later!!
        }
        model.addAttribute("user", user.get());
        return "users/user_profile";
    }
    */
    @GetMapping("/lalala")
    public ResponseEntity<String> getStr() {
        return ResponseEntity.ok(authManager.getUsername());
    }
    @GetMapping("/lalala2")
    public ResponseEntity<String> getStr2() {
        return ResponseEntity.ok("Simple text");
    }

    @GetMapping("/username")
    public ResponseEntity<UserMessage> currentUser() {
        Optional<User> user = userService.getUser(authManager.getUsername());
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserMessage message = new UserMessage(
                user.get().getUsername(),
                user.get().getEmail(),
                user.get().getBio(),
                user.get().getCreations(),
                user.get().getSolves()
                );

        return ResponseEntity.ok(message);
    }
}
