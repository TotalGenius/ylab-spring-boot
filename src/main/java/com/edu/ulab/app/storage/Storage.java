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
        users = UserTable.getUserTable();
        books = BookTable.getBookTable();
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

}
