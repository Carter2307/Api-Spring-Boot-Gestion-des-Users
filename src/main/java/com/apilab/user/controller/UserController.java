package com.apilab.user.controller;

import com.apilab.user.model.User;
import com.apilab.user.repository.UserRepository;
import com.apilab.user.service.UserService;
import com.apilab.user.utils.Patcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    Patcher patcher;

    public UserController(UserService userService, UserRepository userRepository) {
        super();
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        try {
            Optional<User> u = userService.getById(Long.parseLong(id));

            if(u.isPresent()) {
                return new ResponseEntity<User>(u.get(), HttpStatus.OK);
            }

            return null;

        }catch (NumberFormatException nfe) {
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateFieldById(@PathVariable("id") String id, @RequestBody User incompleteUser) {

        //RETRIEVE THE ASSOCIATED USER FROM DATABASE THROUGH ITS ID
        Optional<User> existingUser = userRepository.findById(Long.parseLong(id));

        try {
            //SEND BOTH THE EXISTING INTERN AND THE INCOMPLETE INTERN TO THE INTERNPATCHER
            patcher.userPatcher(existingUser.get(), incompleteUser);
            //SAVE THE UPDATED EXISTING INTERN
            userRepository.save(existingUser.get());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(existingUser.get(), HttpStatus.OK);
    }

    @DeleteMapping()
    public Nullable deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(Long.parseLong(id));
        return null;
    }
}
