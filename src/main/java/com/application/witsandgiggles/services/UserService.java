package com.application.witsandgiggles.services;

import com.application.witsandgiggles.domain.User;
import com.application.witsandgiggles.models.UserModel;
import com.application.witsandgiggles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final transient UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User insert(UserModel userModel) throws IllegalArgumentException {
        User user = userModel.getUser();
        return userRepo.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }
}
