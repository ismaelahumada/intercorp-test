package com.intercorp.challenge.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface IClientDao<T> {
    Optional<T> get(long id);

    List<T> getAll();

    Optional<T> save(T p);

    void delete(T p);

    Double getAgeAverage();

    List<Integer> getAllAges();
}
