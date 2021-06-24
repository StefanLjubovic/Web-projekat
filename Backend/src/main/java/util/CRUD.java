package util;

import java.util.List;

public interface CRUD<T> {
    public Boolean create(T obj);
    public List<T> getAll();
    public T getOne(String id);
    public Boolean delete(String id);
}
