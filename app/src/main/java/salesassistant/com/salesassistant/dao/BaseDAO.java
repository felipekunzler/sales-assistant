package salesassistant.com.salesassistant.dao;

import java.util.List;

/**
 * Base data access object interface
 */
public interface BaseDAO<T> {

    public long addItem(T item);

    public void addItems(List<T> items);

    public int updateItem(T item);

    public T getItem(long id);

    public List<T> getItems();

    public int deleteItem(long id);
}
