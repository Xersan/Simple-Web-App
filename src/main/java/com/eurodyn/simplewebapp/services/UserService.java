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

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id) throws UserNotFoundException {
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.createWith(id)));
    }

    public Optional<User> deleteUser(Long id) throws UserNotFoundException {
        Optional<User> user = getUser(id);
        userRepository.deleteById(id);
        return user;
    }

    public Optional<User> updateUser(User newUser, Long id) throws UserNotFoundException {
        return Optional.ofNullable(userRepository.findById(id)
                .map(oldUser -> {
                    oldUser.setName(newUser.getName());
                    oldUser.setSurname(newUser.getSurname());
                    oldUser.setGender(newUser.getGender());
                    oldUser.setDate(newUser.getDate());
                    return userRepository.save(oldUser);
                })
                .orElseThrow(() -> UserNotFoundException.createWith(id)));
    }
}


