package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dao.UserDAO;
import com.edu.ulab.app.dao.impl.UserDAOImpl;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.mapper.UserMapperImpl;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();
    private final UserMapper userMapper = new UserMapperImpl();

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        log.info("Got user from user DTO{}", user);
        user = userDAO.createUser(user);
        log.info("Got created user from DB{}", user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        log.info("Got user from user DTO{}", user);
        User updatedUser = userDAO.updateUser(user);
        log.info("Got updated user from DB{}", updatedUser);
        return userMapper.userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userDAO.getUserById(id);
        log.info("Got user by id from DB{}", user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto deleteUserById(Long id) {
        User user = userDAO.deleteUser(id);
        log.info("Got deleted user by id from DB{}", user);
        return userMapper.userToUserDto(user);
    }


}
