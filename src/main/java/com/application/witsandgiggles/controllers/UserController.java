package com.application.witsandgiggles.controllers;

import com.application.witsandgiggles.auth.AuthManager;
import com.application.witsandgiggles.auth.AuthenticationService;
import com.application.witsandgiggles.domain.User;
import com.application.witsandgiggles.messages.UserMessage;
import com.application.witsandgiggles.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path ="/api/users")
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

    @GetMapping("/all")
    public ResponseEntity<Iterable<String>> allUsers() {
        return ResponseEntity.ok(userService.getAllUsernames());
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<UserMessage> profile(
            @PathVariable("username") String username
    ) {
        Optional<User> user = userService.getUser(username);
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

    @GetMapping("/username")
    public ResponseEntity<String> currentUser() {
        return ResponseEntity.ok(authManager.getUsername());
    }

    @GetMapping("/update/bio")
    public ResponseEntity<String> updateBio(
            @RequestParam String bio
    ) {
        Optional<User> userOptional = userService.getUser(authManager.getUsername());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        user = userService.updateBio(user, bio);

        return ResponseEntity.ok(user.getBio());
    }

    @GetMapping("/update/email")
    public ResponseEntity<String> updateEmail(
            @RequestParam String email
    ) {
        Optional<User> userOptional = userService.getUser(authManager.getUsername());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        user = userService.updateEmail(user, email);

        return ResponseEntity.ok(user.getEmail());
    }
}
