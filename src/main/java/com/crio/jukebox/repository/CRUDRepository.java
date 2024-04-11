package com.crio.jukebox.repository;

//import java.util.Map;
//import com.crio.jukebox.entities.User;
//import com.crio.jukebox.entities.playlist;

/*public interface CRUDRepository {
    public void create(int id,String name);
    public  void pcreate();
    public void delete();
    public void modify();
    
}*/

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T, ID> {

    public T save(T entity);

    public List<T> findAll();

    public Optional<T> findById(ID id);

    boolean existsById(ID id);

    public void delete(T entity);

    public void deleteById(ID id);

    public long count();

}

/*public interface CRUDRepository<T,ID> {/
    public T save(T entity);
    public List<T> findAll();
    public Optional<T> findById(ID id);
    boolean existsById(ID id);
    public void delete(T entity);
    public void deleteById(ID id);
    public long count();*/

