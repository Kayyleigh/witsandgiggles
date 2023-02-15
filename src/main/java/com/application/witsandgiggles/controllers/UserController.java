package com.application.witsandgiggles.controllers;

import com.application.witsandgiggles.auth.AuthenticationService;
import com.application.witsandgiggles.auth.RegisterRequest;
import com.application.witsandgiggles.domain.User;
import com.application.witsandgiggles.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;


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
}
