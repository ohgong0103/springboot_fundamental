package com.example.identity_service.controller;

import com.example.identity_service.dto_request.ApiResponse;
import com.example.identity_service.dto_request.UserCreateRequest;
import com.example.identity_service.dto_request.UserUpdateRequest;
import com.example.identity_service.entity.User;
import com.example.identity_service.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreateRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
     return   apiResponse;
    }
    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();

    }
    @GetMapping("/{userId}")
    User getUser(@PathVariable String userId){
        return userService.getUserById(userId);
    }
    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId,request);

    }
    @DeleteMapping("/{userId}")
    String deleteUser (@PathVariable String userId){
       userService.deleteUser(userId);
       return "User deleted successfully";
    }



}
