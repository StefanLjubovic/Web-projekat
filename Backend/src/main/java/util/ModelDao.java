package util;

import java.util.List;

public interface ModelDao<T> {
    public  Boolean create(T obj);
    public  List<T> getAll();
    public  T getOne(String id);
    public  Boolean delete(String id);
    public Boolean objectExists(T object);
}
