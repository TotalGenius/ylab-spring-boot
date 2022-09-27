package com.edu.ulab.app.storage;

import java.util.List;
import java.util.Optional;

public interface Table<T> {
    public Optional<T> get(Long id);

    public Optional<List<T>> getAll();

    public T create(T element);

    public Optional<T> delete(Long id);

    public Optional<T> update(T element);

}
