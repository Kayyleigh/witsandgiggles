package com.application.witsandgiggles.controllers;

import com.application.witsandgiggles.domain.User;
import com.application.witsandgiggles.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("userModel", user);
        return "users/register_form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userModel") User user) {
        try {
            userService.insert(user);
            return "users/register_success";
        } catch (Exception e) {
            return "users/register_form";
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
