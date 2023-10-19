package com.eurodyn.simplewebapp.controllers;

import com.eurodyn.simplewebapp.exceptions.UserNotFoundException;
import com.eurodyn.simplewebapp.models.User;
import com.eurodyn.simplewebapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public @ResponseBody ResponseEntity<Optional<User>> getUserById(@PathVariable Long id)
            throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<User> addNewUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PutMapping(path = "/user/{id}")
    public @ResponseBody ResponseEntity<Optional<User>> updateUserById(@Valid @RequestBody User user,
                                                                       @PathVariable Long id)
            throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{id}")
    public @ResponseBody ResponseEntity<Optional<User>> deleteUserById(@PathVariable Long id)
                                                                       throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteall")
    public @ResponseBody ResponseEntity<String> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>("Deleted all users", HttpStatus.OK);
    }
}
