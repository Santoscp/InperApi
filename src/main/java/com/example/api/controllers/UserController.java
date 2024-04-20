package com.example.api.controllers;

import com.example.api.Exception.RecordNotFoundException;
import com.example.api.model.Usuario;
import com.example.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Usuario> GetAllUsers() {
        return userService.GetAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        Optional<Usuario> entity = userService.GetUserById(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Usuario> getUserByName(@PathVariable("name") String name)
            throws RecordNotFoundException {
        List<Usuario> entity = userService.GetUserByName(name);

        if (entity.isEmpty()) {
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(entity.get(0), new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping
    public Usuario saveUser(@RequestBody Usuario user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
