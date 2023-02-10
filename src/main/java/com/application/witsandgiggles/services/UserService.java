package com.application.witsandgiggles.services;

import com.application.witsandgiggles.domain.User;
import com.application.witsandgiggles.models.UserModel;
import com.application.witsandgiggles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<User> getUser(Long id) {
        if (!userRepo.existsById(id)) {
            return Optional.empty();
        } else {
            return userRepo.findById(id);
        }
    }
}
