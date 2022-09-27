package com.edu.ulab.app.dao.impl;

import com.edu.ulab.app.dao.UserDAO;
import com.edu.ulab.app.entity.Person;
import com.edu.ulab.app.exception.NotFoundUserWithSuchIdException;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public class UserDAOImpl implements UserDAO {

    private Storage storage = Storage.getStorage();


    @Override
    public Person getUserById(Long id) {
        Optional<Person> userOptional = Optional.ofNullable(storage.getUserTable().getTable().get(id));
        Person user = userOptional.orElseThrow(()->new NotFoundUserWithSuchIdException(id));

        return user;
    }

    @Override
    public Person createUser(Person user) {
       Person createdUser = storage.getUserTable().create(user);
       return createdUser;
    }

    @Override
    public Person deleteUser(Long id) {
        Optional<Person> optionalUser = storage.getUserTable().delete(id);
        Person user = optionalUser.orElseThrow(()->new NotFoundUserWithSuchIdException(id));
        return user;
    }

    @Override
    public Person updateUser(Person user) {
        System.out.println(user);
        Optional<Person> optionalUser = storage.getUserTable().update(user);
        Person updatedUser = optionalUser.orElseThrow(()->new NotFoundUserWithSuchIdException(user.getId()));
        return updatedUser;
    }


}
