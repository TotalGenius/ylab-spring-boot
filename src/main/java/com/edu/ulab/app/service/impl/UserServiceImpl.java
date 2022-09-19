package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dao.UserDAO;
import com.edu.ulab.app.dao.impl.UserDAOImpl;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    UserDAO userDAO = new UserDAOImpl();
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User(0L, userDto.getFullName(), userDto.getTitle(), userDto.getAge());
        user =  userDAO.createUser(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        User oldUser = userDAO.updateUser(new User(userDto.getId(), userDto.getFullName(), userDto.getTitle(), userDto.getAge()));
        return new UserDto(oldUser.getId(), oldUser.getName(), oldUser.getTitle(), oldUser.getAge());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userDAO.getUserById(id);
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getTitle(), user.getAge());
        return userDto;
    }

    @Override
    public UserDto deleteUserById(Long id) {
       User user= userDAO.deleteUser(id);
       UserDto deletedUser  =new UserDto(user.getId(), user.getName(), user.getTitle(), user.getAge());
       return deletedUser;
    }


}
