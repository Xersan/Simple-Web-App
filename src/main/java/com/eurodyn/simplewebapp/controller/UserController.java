package com.eurodyn.simplewebapp.controller;

import com.eurodyn.simplewebapp.exception.UserNotFoundException;
import com.eurodyn.simplewebapp.model.User;
import com.eurodyn.simplewebapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/all")
    @ResponseBody
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    @ResponseBody
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping(path="/add")
    @ResponseBody
    public ResponseEntity<User> addNewUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PutMapping(path = "/user/{id}")
    @ResponseBody
    public ResponseEntity<Optional<User>> updateUserById(@Valid @RequestBody User user, @PathVariable Long id)
                                                                                        throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{id}")
    @ResponseBody
    public ResponseEntity<Optional<User>> deleteUserById(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteall")
    @ResponseBody
    public ResponseEntity<String> deleteAllUsers() {
        return new ResponseEntity<>(userService.deleteAllUsers(), HttpStatus.OK);
    }

}
