package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserTable implements Table<User> {
    private Long currentUserId;
    private Map<Long, User> users;

    public static UserTable userTable;

    private UserTable() {
        currentUserId = 1L;
        users = new HashMap<>();
    }

    public static UserTable getUserTable() {
        if (userTable==null) userTable = new UserTable();
        return userTable;
    }

    public Map<Long, User> getTable() {
        return users;
    }
    private Long setUserId() {
        return currentUserId++;
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.ofNullable(users.values().stream().toList());
    }

    @Override
    public User create(User element) {
        element.setId(setUserId());
        users.put(element.getId(), element);
        return element;
    }

    @Override
    public Optional<User> delete(Long id) {
        return Optional.ofNullable(users.remove(id));
    }

    @Override
    public Optional<User> update(User element) {
        User user = null;
        if (users.containsKey(element.getId())) {
            User tempUser = users.get(element.getId());
            if (element.getFullName() != null) tempUser.setFullName(element.getFullName());
            if (element.getTitle() != null) tempUser.setTitle(element.getTitle());
            if (element.getAge() != 0) tempUser.setAge(element.getAge());
            user = tempUser;
        }
        return Optional.ofNullable(user);
    }
}