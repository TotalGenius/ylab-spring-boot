package com.edu.ulab.app.dao;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;

import java.util.List;

public interface UserDAO {

    public User getUserById(Long id);
    public User createUser(User user);

    public User deleteUser(Long id);

    User updateUser(User user);
}
