package com.eurodyn.simplewebapp.controllers;

import com.eurodyn.simplewebapp.models.User;
import com.eurodyn.simplewebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.Optional;

@Controller
@RequestMapping(path="/demo")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String surname,
                                           @RequestParam char gender, @RequestParam String date,
                                           @RequestParam String ... addresses) {

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setGender(gender);
        user.setDate(date);
        String workAddress = addresses.length > 0 ? addresses[0] : null;
        String homeAddress = addresses.length > 1 ? addresses[1] : null;
        user.setWorkAddress(workAddress);
        user.setHomeAddress(homeAddress);

        return "Saved";
    }

    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


}
