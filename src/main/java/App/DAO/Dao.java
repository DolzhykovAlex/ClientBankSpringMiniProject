package App.DAO;


import java.util.List;

public interface Dao<T> {



    public T save(T obj);

    public boolean delete(T obj);

    public void deleteAll(List<T> entities);

    public void saveAll(List<T> entities);

    public List<T> findAll();

    public boolean deleteById(long id);

    public T getOne(long id);


}
