package com.company.dao;

public interface CRUDDao<T> {
    public void create (T object);
    public T read (Long id);
    public void update (T object);
    public void delete (T object);
}
