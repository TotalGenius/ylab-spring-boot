package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Storage {
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции


    /*
    Попробовал сделать хранилище на примере обычной базы данных,
    т.е. Storage - наша база данных, а UserTable и BookTable - таблицы в это БД
     */
    private static Storage storage;
    private UserTable users;
    private BookTable books;

    private Storage() {
        users = new UserTable();
        books = new BookTable();
    }

    public static Storage getStorage() {
        if (storage == null) storage = new Storage();
        return storage;
    }

    public UserTable getUserTable() {
        return users;
    }

    public BookTable getBookTable() {
        return books;
    }


    ////////////////////////////////
    public class UserTable implements Table<User> {
        private Long currentUserId;
        private Map<Long, User> users;

        private UserTable() {
            currentUserId = 1L;
            users = new HashMap<>();
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
            User user;
            if (users.containsKey(element.getId())) {
                User tempUser = users.get(element.getId());
                user = new User(tempUser.getId(), tempUser.getName(), tempUser.getTitle(), tempUser.getAge());
                if (element.getName() != null) tempUser.setName(element.getName());
                if (element.getTitle() != null) tempUser.setTitle(element.getTitle());
                if (element.getAge() != 0) tempUser.setAge(element.getAge());
            } else user = new User(0L, "", "", 0);
            return Optional.ofNullable(user);
        }
    }
    ////////////////////////////////
    public class BookTable implements Table<Book> {
        private Long currentBookId;
        private Map<Long, Book> books;

        private BookTable() {
            currentBookId = 1L;
            books = new HashMap<>();
        }

        private Long setBookId() {
            return currentBookId++;
        }

        @Override
        public Optional<Book> get(Long id) {
            return Optional.ofNullable(books.get(id));
        }

        @Override
        public Optional<List<Book>> getAll() {
            return  Optional.ofNullable(books.values().stream().toList());
        }

        @Override
        public Book create(Book element) {
            element.setId(setBookId());
            books.put(element.getId(), element);
            return element;
        }

        @Override
        public Optional<Book> delete(Long id) {
            return Optional.ofNullable(books.remove(id)) ;
        }

        @Override
        public Optional<Book> update(Book element) {

            Optional<Book> bookOptional = Optional.empty();

            if (books.containsKey(element.getUserId())&&books.values().stream().noneMatch(x -> x.getTitle().compareToIgnoreCase(element.getTitle()) == 0
                    && x.getAuthor().compareToIgnoreCase(element.getAuthor()) == 0)) {
                element.setId(setBookId());
                books.put(element.getId(),element);
                Book temp = books.get(element.getId());
                bookOptional= Optional.ofNullable(new Book(temp.getId(), temp.getUserId(), temp.getTitle(),temp.getAuthor(),temp.getPageCount()));
            }
            return bookOptional;
        }
    }
}
