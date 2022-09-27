package com.edu.ulab.app.dao;

import com.edu.ulab.app.entity.Person;

public interface UserDAO {

    public Person getUserById(Long id);
    public Person createUser(Person user);

    public Person deleteUser(Long id);

    Person updateUser(Person user);
}
