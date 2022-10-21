package com.codingboot.Core.controller.Auth;

import com.codingboot.Core.domain.entity.User;
import com.codingboot.Core.service.Auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<User> getUsersId(@PathVariable String name){
        return ResponseEntity.ok().body(userService.getUser(name));
    }
}
