package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dao.UserDAO;
import com.edu.ulab.app.dao.impl.UserDAOImpl;
import com.edu.ulab.app.dto.BookDto;
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
        userDto.setId(user.getUserId());
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userDAO.getUserById(id);
        UserDto userDto = new UserDto(user.getUserId(), user.getName(), user.getTitle(), user.getAge());
        return userDto;
    }

    @Override
    public UserDto deleteUserById(Long id) {
       User user= userDAO.deleteUser(id);
       UserDto deletedUser  =new UserDto(user.getUserId(), user.getName(), user.getTitle(), user.getAge());
       return deletedUser;
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> userDtos = userDAO.getAll().stream()
                .map(x-> new UserDto(x.getUserId(),x.getName(),x.getTitle(),x.getAge()))
                .toList();
        return userDtos;
    }
}
