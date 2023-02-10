package com.application.witsandgiggles.controllers;

import com.application.witsandgiggles.domain.User;
import com.application.witsandgiggles.models.UserModel;
import com.application.witsandgiggles.repository.UserRepository;
import com.application.witsandgiggles.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        UserModel userModel = new UserModel();
        model.addAttribute("userModel", userModel);
        return "users/register_form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userModel") UserModel userModel) {
        try {
            userService.insert(userModel);
            return "users/register_success";
        } catch (Exception e) {
            return "users/register_form"; //TODO let the user know they should choose different name
        }
    }
}
