package org.edu.myclass360.controller;

import org.edu.myclass360.dto.CreateUserDTO;
import org.edu.myclass360.dto.UpdateUserDto;
import org.edu.myclass360.dto.UserDTO;
import org.edu.myclass360.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.findAll();
    }


    @PostMapping
    public ResponseEntity<UserDTO> createUser (@RequestBody CreateUserDTO dto){
        return new ResponseEntity<>(userService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{dni}")
    public UserDTO getUserByDni(@PathVariable String dni){
        return userService.findByDni(dni);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto){
        return userService.update(id, updateUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactiveUser(@PathVariable Long id){
        userService.deactive(id);
        return ResponseEntity.noContent().build();
    }

}
