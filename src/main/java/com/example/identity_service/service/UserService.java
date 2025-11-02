package com.example.identity_service.service;

import com.example.identity_service.dto.request.UserCreateRequest;
import com.example.identity_service.dto.request.UserUpdateRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.entity.User;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.exception.ErrorCode;
import com.example.identity_service.mapper.UserMapper;
import com.example.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public User createUser(UserCreateRequest request) {
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTS);

        User user = userMapper.toUser(request);


        return userRepository.save(user);
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponse getUserById(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User not found")));
    }
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }


    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

}
