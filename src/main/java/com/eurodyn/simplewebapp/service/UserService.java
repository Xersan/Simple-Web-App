package com.eurodyn.simplewebapp.service;

import com.eurodyn.simplewebapp.exception.UserNotFoundException;
import com.eurodyn.simplewebapp.model.User;
import com.eurodyn.simplewebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) throws UserNotFoundException {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> UserNotFoundException.createWith(id)));
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(User newUser, Long id) throws UserNotFoundException {
        return Optional.ofNullable(userRepository.findById(id)
                .map(oldUser -> {
                    oldUser.setName(newUser.getName());
                    oldUser.setSurname(newUser.getSurname());
                    oldUser.setGender(newUser.getGender());
                    oldUser.setDate(newUser.getDate());

                    if (newUser.getAddress() != null) {
                        if (oldUser.getAddress() != null) {
                            oldUser.getAddress().setWorkAddress(newUser.getAddress().getWorkAddress());
                            oldUser.getAddress().setHomeAddress(newUser.getAddress().getHomeAddress());
                        }
                        else
                            oldUser.setAddress(newUser.getAddress());
                    }

                    return userRepository.save(oldUser);
                })
                .orElseThrow(() -> UserNotFoundException.createWith(id)));
    }

    public Optional<User> deleteUser(Long id) throws UserNotFoundException {
        Optional<User> user = getUser(id);
        userRepository.deleteById(id);
        return user;
    }

    @SuppressWarnings("SameReturnValue")
    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "Deleted all users";
    }

}
