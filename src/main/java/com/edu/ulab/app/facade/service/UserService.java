package com.edu.ulab.app.facade.service;

import com.edu.ulab.app.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto getUserById(Long id);

    UserDto deleteUserById(Long id);

}
