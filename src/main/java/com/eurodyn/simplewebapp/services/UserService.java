package com.eurodyn.simplewebapp.services;

import com.eurodyn.simplewebapp.exceptions.UserNotFoundException;
import com.eurodyn.simplewebapp.models.User;
import com.eurodyn.simplewebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    public Optional<User> userById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent())
            return user;
        else
            throw UserNotFoundException.createWith(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

}


