package com.my.steinfield.Steinfield.controllers;

import com.my.steinfield.Steinfield.exceptions.DataInvalidException;
import com.my.steinfield.Steinfield.exceptions.DataNotFoundException;
import com.my.steinfield.Steinfield.models.User;
import com.my.steinfield.Steinfield.models.dto.UserDTO;
import com.my.steinfield.Steinfield.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder crypto;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> index() {
        try {
            List<User> userList = this.userService.index();
            List<UserDTO> userDTOList = userList.stream().map(UserDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok().body(userDTOList);
        } catch (Exception ex) {
            throw new DataNotFoundException(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> show(@PathVariable Long id) {
        try {
            User currentUser = this.userService.show(id);
            UserDTO currentUserDTO = new UserDTO(currentUser);
            return ResponseEntity.ok().body(currentUserDTO);
        } catch (DataNotFoundException ex) {
            String msg = "User not found id: " + id;
            throw new DataNotFoundException(msg);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserDTO> store(@RequestBody User newUser){
        try {
            newUser.setPassword(crypto.encode(newUser.getPassword()));
            UserDTO newUserSaved = new UserDTO(this.userService.store(newUser));
            return ResponseEntity.status(HttpStatus.CREATED).body(newUserSaved);
        } catch (DataInvalidException e) {
            throw new DataInvalidException(e.getMessage(), e);
        }
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
//    @PutMapping( value = "/{id}")
//    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody User currentUser) {
//        try {
//
//        } catch () {
//
//        }
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<HttpStatus> destroy(@PathVariable Long id) {
//        try {
//
//        } catch () {
//
//        }
//    }
}