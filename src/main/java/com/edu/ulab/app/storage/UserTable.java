package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserTable implements Table<Person> {
    private Long currentUserId;
    private Map<Long, Person> users;

    public static UserTable userTable;

    private UserTable() {
        currentUserId = 1L;
        users = new HashMap<>();
    }

    public static UserTable getUserTable() {
        if (userTable==null) userTable = new UserTable();
        return userTable;
    }

    public Map<Long, Person> getTable() {
        return users;
    }
    private Long setUserId() {
        return currentUserId++;
    }

    @Override
    public Optional<Person> get(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<List<Person>> getAll() {
        return Optional.ofNullable(users.values().stream().toList());
    }

    @Override
    public Person create(Person element) {
        element.setId(setUserId());
        users.put(element.getId(), element);
        return element;
    }

    @Override
    public Optional<Person> delete(Long id) {
        return Optional.ofNullable(users.remove(id));
    }

    @Override
    public Optional<Person> update(Person element) {
        Person user = null;
        if (users.containsKey(element.getId())) {
            Person tempUser = users.get(element.getId());
            if (element.getFullName() != null) tempUser.setFullName(element.getFullName());
            if (element.getTitle() != null) tempUser.setTitle(element.getTitle());
            if (element.getAge() != 0) tempUser.setAge(element.getAge());
            user = tempUser;
        }
        return Optional.ofNullable(user);
    }
}