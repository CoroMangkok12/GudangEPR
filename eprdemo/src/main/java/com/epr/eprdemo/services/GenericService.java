package com.epr.eprdemo.services;

import java.util.List;


public interface GenericService<T> {
    T save(T entity);
    T update(T entity);
    void deleteById(Integer id);
    T findById(Integer id);
    List<T> findAll();
}
