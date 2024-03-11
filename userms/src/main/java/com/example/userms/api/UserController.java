package com.example.userms.api;

import com.example.userms.UserRepository;
import com.example.userms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * swagger-ui url: http://localhost:8080/swagger-ui/index.html
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // {id} a variable in the url
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> userFound = userRepo.findById(id);
        if (userFound.isPresent()) {
            return ResponseEntity.ok(userFound.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/users")
    // @RequestBody - json object will be converted to Java Object
    public User addUser(@RequestBody User user) {
        User savedUser = userRepo.save(user);
        return savedUser;
    }
}
