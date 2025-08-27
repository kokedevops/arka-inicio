package com.skillnest.arka.controller;

import com.skillnest.arka.dto.UserCreateDTO;
import com.skillnest.arka.dto.UserDTO;
import com.skillnest.arka.model.User;
import com.skillnest.arka.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getUserViewUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<UserDTO> getUser(@PathVariable Long id){ //id=1
        UserDTO user = userService.getUserById(id);
        if (user== null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping()
    public  ResponseEntity<UserDTO> createUser (@Valid @RequestBody UserCreateDTO user){
        UserDTO newUser = userService.createUser(user);
        return  new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long id, @Valid @RequestBody UserCreateDTO user){
        UserDTO updatedUser = userService.updateUser(id, user);
        if (updatedUser== null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id){
        if (userService.deleteUser(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
