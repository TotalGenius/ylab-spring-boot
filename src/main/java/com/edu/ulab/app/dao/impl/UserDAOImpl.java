package com.edu.ulab.app.dao.impl;

import com.edu.ulab.app.dao.UserDAO;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Repository
public class UserDAOImpl implements UserDAO {

    private Storage storage = Storage.getStorage();


    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = storage.getUserTable().get(id);
        User user = userOptional.orElse(new User(0L,"","", 0));
        return user;
    }

    @Override
    public User createUser(User user) {
       User createdUser = storage.getUserTable().create(user);
       return user;
    }

    @Override
    public User deleteUser(Long id) {
        Optional<User> optionalUser = storage.getUserTable().delete(id);
        User user = optionalUser.orElse(new User(0L,"","", 0));
        return user;
    }

    @Override
    public User updateUser(User user) {
        Optional<User> optionalUser = storage.getUserTable().update(user);
        User updatedUser = optionalUser.orElse(new User(0L,"","", 0));
        return updatedUser;
    }


}
