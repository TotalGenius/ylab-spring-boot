package com.edu.ulab.app.dao.impl;

import com.edu.ulab.app.dao.UserDAO;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundUserWithSuchIdException;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public class UserDAOImpl implements UserDAO {

    private Storage storage = Storage.getStorage();


    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = Optional.ofNullable(storage.getUserTable().getTable().get(id));
        User user = userOptional.orElseThrow(()->new NotFoundUserWithSuchIdException(id));

        return user;
    }

    @Override
    public User createUser(User user) {
       User createdUser = storage.getUserTable().create(user);
       return createdUser;
    }

    @Override
    public User deleteUser(Long id) {
        Optional<User> optionalUser = storage.getUserTable().delete(id);
        User user = optionalUser.orElseThrow(()->new NotFoundUserWithSuchIdException(id));
        return user;
    }

    @Override
    public User updateUser(User user) {
        System.out.println(user);
        Optional<User> optionalUser = storage.getUserTable().update(user);
        User updatedUser = optionalUser.orElseThrow(()->new NotFoundUserWithSuchIdException(user.getId()));
        return updatedUser;
    }


}
